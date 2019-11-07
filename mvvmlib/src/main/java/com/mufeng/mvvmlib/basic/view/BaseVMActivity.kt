package com.mufeng.mvvmlib.basic.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.observe
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView
import com.mufeng.mvvmlib.basic.BaseViewModel
import com.mufeng.mvvmlib.basic.UIChange
import com.mufeng.mvvmlib.basic.ViewStatus
import com.mufeng.mvvmlib.basic.eventObserver
import com.mufeng.mvvmlib.ext.fillIntentArguments
import com.mufeng.mvvmlib.ext.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/14 22:39
 * @描述
 */
abstract class BaseVMActivity<VM : BaseViewModel, VB: ViewDataBinding> : AppCompatActivity(), LifecycleObserver, CoroutineScope by MainScope() {

    protected lateinit var binding: VB

    abstract val viewModel: VM
    abstract val layoutResId: Int

    lateinit var loadingView: LoadingPopupView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        viewModel.let(lifecycle::addObserver)
        startObserve()
        binding.lifecycleOwner = this
        loadingView = XPopup.Builder(this)
            .dismissOnTouchOutside(false)
            .asLoading()

        initView(savedInstanceState)
        initData()
    }

    open  fun startObserve() {
        viewModel.apply {
            viewStatus.eventObserver(this@BaseVMActivity){
                when(it) {
                    ViewStatus.LOADING -> showLoading()
                    ViewStatus.DONE -> hideLoading()
                    ViewStatus.ERROR -> hideLoading()
                }
            }

            uiChange.eventObserver(this@BaseVMActivity) {
                when (it) {
                    is UIChange.ToastEvent -> toast { it.msg }
                    is UIChange.FinishEvent -> finish()
                    is UIChange.IntentEvent -> startActivity(it)
                }
            }

            exception.observe(this@BaseVMActivity) {
                onError(it)
            }

        }
    }

    private fun showLoading(){
        loadingView.show()
    }

    private fun hideLoading(){
        loadingView.dismiss()
    }

    private fun startActivity(intentEvent: UIChange.IntentEvent) {
        val intent = Intent(this@BaseVMActivity, intentEvent.clzz)
        if (intentEvent.params.isNotEmpty()) {
            intent.fillIntentArguments(intentEvent.params)
        }
        startActivity(intent)
        if (intentEvent.isFinished) {
            finish()
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