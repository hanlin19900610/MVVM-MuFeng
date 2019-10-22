package com.mufeng.sample.app

import com.alibaba.android.arouter.launcher.ARouter
import com.mufeng.mvvmlib.basic.BaseApplication
import com.mufeng.sample.BuildConfig

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/16 21:18
 * @描述
 */
class App : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

}