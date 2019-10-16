package com.mufeng.mvvmlib.basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/14 22:39
 * @描述
 */
@ExperimentalCoroutinesApi
abstract class BaseVMActivity<VM : BaseViewModel, VB: ViewDataBinding> : AppCompatActivity(), LifecycleObserver, CoroutineScope by MainScope() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: VB

    abstract val layoutResId: Int
    abstract val factory: ViewModelProvider.Factory?
    abstract val providerVMClass: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        initViewModel()
        startObserve()
        binding.lifecycleOwner = this
        initView(savedInstanceState)
        initData()
    }

    private fun initViewModel() {
        providerVMClass.let {
            viewModel = if (factory == null) {
                ViewModelProviders.of(this).get(it)
            }else {
                ViewModelProviders.of(this, factory).get(it)
            }
            viewModel.let(lifecycle::addObserver)
        }
    }

    open  fun startObserve() {

    }

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initData()

    override fun onDestroy() {
        viewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
        cancel()
        binding.unbind()
    }

}