package com.mufeng.mvvmlib.basic.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/14 22:35
 * @描述
 */
abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    abstract val layoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        initView(savedInstanceState)
        initData()
    }


    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}