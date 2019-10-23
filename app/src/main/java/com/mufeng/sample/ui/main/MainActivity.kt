package com.mufeng.sample.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.mufeng.mvvmlib.basic.BaseVMActivity
import com.mufeng.sample.R
import com.mufeng.sample.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class MainActivity : BaseVMActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {


    }

    override fun initData() {
    }
}
