package com.mufeng.mvvmlib.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
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
 * @创建时间 2019/10/15 21:09
 * @描述
 */
@ExperimentalCoroutinesApi
abstract class BaseVMFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment(),
    CoroutineScope by MainScope() {

    protected lateinit var binding: VB
    abstract var viewModel: VM
    abstract val layoutResId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObserve()
        binding.lifecycleOwner = this
        viewModel.let(lifecycle::addObserver)
        initView()
        initData()
    }

    open  fun startObserve() {
        viewModel.apply {
            viewStatus.observe(this@BaseVMFragment){
                when(it) {
                    ViewStatus.LOADING -> toast { "加载中" }
                    ViewStatus.DONE -> toast { "加载完成" }
                    ViewStatus.ERROR -> toast { "请求失败" }
                }
            }

            exception.observe(this@BaseVMFragment) {
                onError(it)
            }

        }
    }

    abstract fun initView()

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
