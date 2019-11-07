package com.mufeng.mvvmlib.http

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mufeng.mvvmlib.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.InputStream
import java.security.KeyStore
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

/**
 * @author MuFeng-T
 * @createTime 2019-10-17
 * @details
 */
abstract class BaseHttpUtils {

    /**
     * Headers
     */
    protected open val header: Map<String, String> = emptyMap()

    /**
     * 超时秒数
     */
    protected open val timeout = 60L

    /**
     * OkHttp的拦截器
     */
    protected open val interceptors: Iterable<Interceptor>
        get() = emptyList()

    /**
     * CallAdapter转换器
     */
    protected open val callAdapterFactories: Iterable<CallAdapter.Factory>
        get() = emptyList()

    /**
     * Converter转换器
     */
    protected open val convertersFactories: Iterable<Converter.Factory>
        get() = emptyList()

    /**
     * Https密钥
     */
    protected open val keyStore: InputStream?
        get() = null

    /**
     * Https密码
     */
    protected open val keyStorePassword: String?
        get() = null

    /**
     * Https证书
     */
    protected open val certificates: Array<InputStream>
        get() = emptyArray()

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.BASIC
            }

            builder.addInterceptor(logging)
                .installHttpsCertificates()
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .addInterceptor(AddHeaderInterceptor(header))
                .apply {
                    interceptors.map(::addInterceptor)
                }

            handleBuilder(builder)

            return builder.build()
        }

    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)

    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .apply {
                convertersFactories.map(::addConverterFactory)
                callAdapterFactories.map(::addCallAdapterFactory)
            }
            .build().create(serviceClass)
    }

    @Suppress("DEPRECATION")
    private fun OkHttpClient.Builder.installHttpsCertificates(): OkHttpClient.Builder {
        if (certificates.isEmpty()) return this
        val x509TrustManager: X509TrustManager = prepareTrustManager(*certificates).fetch()
        val keyManagers = prepareKeyManager(keyStore, keyStorePassword)
        val sslContext = SSLContext.getInstance("TLS")
        val trustManager = X509TrustManagerImpl(x509TrustManager)
        sslContext.init(keyManagers, arrayOf<TrustManager>(trustManager), null)
        return sslSocketFactory(sslContext.socketFactory, x509TrustManager)
    }

    private fun prepareKeyManager(keyInput: InputStream?, password: String?): Array<KeyManager>? {
        keyInput ?: return null
        password ?: return null
        val keyStore = KeyStore.getInstance("BKS")
        keyStore.load(keyInput, password.toCharArray())
        val keyManagerFactory =
            KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        keyManagerFactory.init(keyStore, password.toCharArray())
        return keyManagerFactory.keyManagers
    }

    private fun prepareTrustManager(vararg certificates: InputStream): Array<TrustManager> {
        val certificateFactory = CertificateFactory.getInstance("X.509")
        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(null)
        certificates.forEachIndexed { index, certificate ->
            certificate.use {
                val certificateAlias = index.toString()
                keyStore.setCertificateEntry(
                    certificateAlias,
                    certificateFactory.generateCertificate(it)
                )
            }
        }
        val trustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(keyStore)
        return trustManagerFactory.trustManagers
    }

    private inline fun <reified T> Array<*>.fetch(): T = first { it is T } as T

    private inner class X509TrustManagerImpl(private val localTrustManager: X509TrustManager) :
        X509TrustManager {

        private val defaultTrustManager: X509TrustManager

        init {
            val trustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            @Suppress("CAST_NEVER_SUCCEEDS")
            trustManagerFactory.init(null as? KeyStore)
            defaultTrustManager = trustManagerFactory.trustManagers.fetch()
        }

        override fun checkClientTrusted(chain: Array<out X509Certificate>, authType: String?) {
            println("checkClientTrusted\tchain:${chain.contentToString()}\tauthType:$authType")
        }

        override fun checkServerTrusted(chain: Array<out X509Certificate>, authType: String?) {
            try {
                defaultTrustManager.checkServerTrusted(chain, authType)
            } catch (ce: CertificateException) {
                localTrustManager.checkServerTrusted(chain, authType)
            }
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
    }


}