package com.mufeng.sample.app

import android.app.Application
import com.mufeng.mvvmlib.BuildConfig
import com.mufeng.mvvmlib.ext.initLogger
import com.mufeng.mvvmlib.ext.paging.PagingRequestHelper
import com.mufeng.mvvmlib.http.handler.HeaderInterceptor
import com.mufeng.mvvmlib.http.handler.Request
import com.mufeng.mvvmlib.utils.initContext
import kotlin.properties.Delegates

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/16 21:18
 * @描述
 */
class App : Application(){
    companion object {
        var CONTEXT: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = this
        initContext(this)
        initLogger(BuildConfig.DEBUG)
        Request.init(applicationContext,"https://www.wanandroid.com"){
            okHttp {
                it
            }
            retrofit {
                it
            }
        }
    }
}