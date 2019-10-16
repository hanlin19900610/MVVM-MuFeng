package com.mufeng.sample.bindingDemo

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.mufeng.mvvmlib.basic.BaseVMActivity
import com.mufeng.sample.R
import com.mufeng.sample.databinding.ActivityBindingDemoBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/15 22:22
 * @描述
 */
@ExperimentalCoroutinesApi
class BindingDemoActivity: BaseVMActivity<BindingDemoViewModel, ActivityBindingDemoBinding>(){
    override val layoutResId: Int
        get() = R.layout.activity_binding_demo
    override val factory: ViewModelProvider.Factory?
        get() = null
    override val providerVMClass: Class<BindingDemoViewModel>
        get() = BindingDemoViewModel::class.java

    override fun initView(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        viewModel.aa.observe(this){

        }
    }

    override fun initData() {
    }

}