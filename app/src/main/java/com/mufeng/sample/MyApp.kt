package com.mufeng.sample

import android.app.Application
import timber.log.Timber

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/16 21:18
 * @描述
 */
class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.asTree())
    }
}