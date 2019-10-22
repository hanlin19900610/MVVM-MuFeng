package com.mufeng.sample.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import com.mufeng.mvvmlib.basic.BaseVMActivity
import com.mufeng.sample.R
import com.mufeng.sample.databinding.ActivitySplashBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * @author MuFeng-T
 * @createTime 2019-10-22
 * @details
 */

@ExperimentalCoroutinesApi
class SplashActivity : BaseVMActivity<SplashViewModel, ActivitySplashBinding>() {
    override val viewModel: SplashViewModel by viewModels()
    override val layoutResId: Int = R.layout.activity_splash

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
    }
}