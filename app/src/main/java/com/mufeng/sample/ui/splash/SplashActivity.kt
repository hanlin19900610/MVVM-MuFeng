package com.mufeng.sample.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.mufeng.mvvmlib.basic.view.BaseVMActivity
import com.mufeng.mvvmlib.basic.eventObserver
import com.mufeng.mvvmlib.utils.toast
import com.mufeng.sample.R
import com.mufeng.sample.databinding.ActivitySplashBinding

/**
 * @author MuFeng-T
 * @createTime 2019-10-22
 * @details
 */

class SplashActivity : BaseVMActivity<SplashViewModel, ActivitySplashBinding>() {
    override val viewModel: SplashViewModel by viewModels()
    override val layoutResId: Int = R.layout.activity_splash

    override fun initView(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
    }

    override fun initData() {
        viewModel.time.observe(this) {

        }

        viewModel.test.eventObserver(this){
            toast(it)
        }
    }
}