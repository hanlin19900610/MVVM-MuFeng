package com.mufeng.sample.http

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mufeng.mvvmlib.ext.GsonUtils
import com.mufeng.mvvmlib.http.BaseHttpUtils
import com.mufeng.mvvmlib.utils.context
import com.mufeng.sample.app.App
import com.mufeng.sample.utils.NetWorkUtils
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * @author MuFeng-T
 * @createTime 2019-10-17
 * @details
 */
object WAHttpUtils : BaseHttpUtils(){
    val service by lazy { getService(ApiService::class.java, ApiService.BASE_URL) }

    private val cookieJar by lazy { PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(
        App.CONTEXT)) }

    override val header: Map<String, String>
        get() = mapOf("Accept-Language" to "zh-cn,zh;q=0.8")

    override val callAdapterFactories: Iterable<CallAdapter.Factory>
        get() = listOf(CoroutineCallAdapterFactory())

    override val convertersFactories: Iterable<Converter.Factory>
        get() = listOf(GsonConverterFactory.create(GsonUtils.INSTANCE))

    override fun handleBuilder(builder: OkHttpClient.Builder) {

        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        builder.cache(cache)
            .cookieJar(cookieJar)
            .addInterceptor { chain ->
                var request = chain.request()
                if (!NetWorkUtils.isNetworkAvailable(context)) {
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }
                val response = chain.proceed(request)
                if (!NetWorkUtils.isNetworkAvailable(context)) {
                    val maxAge = 60 * 60
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                } else {
                    val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }

                response
            }
    }
}