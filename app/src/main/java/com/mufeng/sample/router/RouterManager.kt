package com.mufeng.sample.router

import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author MuFeng-T
 * @createTime 2019-10-22
 * @details
 */
object RouterPath {

    const val MAIN_ACTIVITY = "/app/main/MainActivity"

}

object RouterManager {

    fun startMainActivity() =
        ARouter.getInstance().build(RouterPath.MAIN_ACTIVITY)
            .navigation()

}
