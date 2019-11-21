package com.mufeng.mvvmlib.basic.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView
import com.mufeng.mvvmlib.basic.*
import com.mufeng.mvvmlib.ext.fillIntentArguments
import com.mufeng.mvvmlib.utils.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/15 21:09
 * @描述
 */
abstract class BaseVMFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment(),
    CoroutineScope by MainScope() {

    protected lateinit var binding: VB
    abstract val viewModel: VM
    abstract val layoutResId: Int
    lateinit var loadingView: LoadingPopupView


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
        loadingView = XPopup.Builder(activity)
            .dismissOnTouchOutside(false)
            .asLoading()
        binding.lifecycleOwner = this
        initView()
        initData()
    }

    open  fun startObserve() {
        viewModel.apply {

            uiChange.eventObserver(this@BaseVMFragment) {
                when (it) {
                    is UIChange.ToastEvent -> BaseApplication.CONTEXT.toast(it.msg)
                    is UIChange.FinishEvent -> activity?.finish()
                    is UIChange.IntentEvent -> startActivity(it)
                }
            }

        }
    }

    abstract fun initView()

    abstract fun initData()

    open fun onError(e: Throwable) {}

    private fun showLoading(){
        loadingView.show()
    }

    private fun hideLoading(){
        loadingView.dismiss()
    }
    private fun startActivity(intentEvent: UIChange.IntentEvent) {
        val intent = Intent(activity, intentEvent.clzz)
        if (intentEvent.params.isNotEmpty()) {
            intent.fillIntentArguments(intentEvent.params)
        }
        startActivity(intent)
        if (intentEvent.isFinished) {
            activity?.finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
        binding.unbind()
    }

}
