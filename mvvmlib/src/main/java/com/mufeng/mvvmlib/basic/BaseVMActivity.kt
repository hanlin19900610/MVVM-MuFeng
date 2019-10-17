package com.mufeng.mvvmlib.basic

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.mufeng.mvvmlib.ext.toast
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

    protected lateinit var binding: VB

    abstract val viewModel: VM
    abstract val layoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        viewModel.let(lifecycle::addObserver)
        startObserve()
        binding.lifecycleOwner = this
        initView(savedInstanceState)
        initData()
    }

    open  fun startObserve() {
        viewModel.apply {
            viewStatus.observe(this@BaseVMActivity){
                when(it) {
                    ViewStatus.LOADING -> toast { "加载中" }
                    ViewStatus.DONE -> toast { "加载完成" }
                    ViewStatus.ERROR -> toast { "请求失败" }
                }
            }

            exception.observe(this@BaseVMActivity) {
                onError(it)
            }

        }
    }

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initData()

    open fun onError(e: Throwable) {}

    override fun onDestroy() {
        viewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
        cancel()
        binding.unbind()
    }

}