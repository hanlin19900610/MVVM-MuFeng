package com.mufeng.mvvmlib.basic

import android.app.Application
import com.mufeng.mvvmlib.BuildConfig
import com.mufeng.mvvmlib.ext.initLogger
import kotlin.properties.Delegates

/**
 * @author MuFeng-T
 * @createTime 2019-10-17
 * @details
 */
open class BaseApplication : Application(){

    companion object {
        var CONTEXT: BaseApplication by Delegates.notNull()

    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = this

        initLogger(BuildConfig.DEBUG)
    }

}