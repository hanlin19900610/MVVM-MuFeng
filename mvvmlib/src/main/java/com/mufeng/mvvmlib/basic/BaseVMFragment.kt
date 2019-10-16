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

    protected lateinit var viewModel: VM
    protected lateinit var binding: VB


    abstract val layoutResId: Int

    abstract val factory: ViewModelProvider.Factory?
    abstract val providerVMClass: Class<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,layoutResId,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        startObserve()
        binding.lifecycleOwner = this
        initView()
        initData()
    }

    protected fun startObserve() {

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

    abstract fun initView()

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
