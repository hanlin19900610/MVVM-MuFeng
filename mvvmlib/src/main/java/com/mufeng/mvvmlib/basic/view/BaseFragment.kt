package com.mufeng.mvvmlib.basic.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/15 21:06
 * @描述
 */
abstract class BaseFragment : Fragment(), CoroutineScope by MainScope() {

    abstract val layoutRes: Int
    protected var isDataInitiated: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
        initData()
    }

    abstract fun initView(savedInstanceState: Bundle?)

    protected fun initData(){}


    override fun onResume() {
        super.onResume()
        if (!isDataInitiated) {
            lazyLoadData()
            isDataInitiated = true
        }
    }

    abstract fun lazyLoadData()

    fun refreshData(){
        isDataInitiated = false
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}