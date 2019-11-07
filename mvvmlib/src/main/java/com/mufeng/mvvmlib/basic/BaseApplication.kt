package com.mufeng.mvvmlib.basic

import android.app.Application
import com.mufeng.mvvmlib.BuildConfig
import com.mufeng.mvvmlib.ext.initLogger
import com.mufeng.mvvmlib.utils.initContext
import kotlin.properties.Delegates

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/6 16:15
 * @描述
 */
open class BaseApplication : Application() {
    companion object {
        var CONTEXT: BaseApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = this
        initContext(this)
        initLogger(BuildConfig.DEBUG)
    }
}