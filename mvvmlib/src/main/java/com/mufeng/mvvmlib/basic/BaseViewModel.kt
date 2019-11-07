package com.mufeng.mvvmlib.basic

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.mufeng.mvvmlib.ext.IOScope
import com.mufeng.mvvmlib.widget.State
import kotlinx.coroutines.*

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/14 22:20
 * @描述
 */

open class BaseViewModel : ViewModel(), CoroutineScope by IOScope(){

    /**
     * 界面状态
     */
    private val _loadStateLiveData = MutableLiveData<Pair<State, String>>()
    val loadStateLiveData: LiveData<Pair<State, String>> = _loadStateLiveData

    /**
     * 网络加载状态
     */
    private val _viewStatus = MutableLiveData<Event<ViewStatus>>()
    val viewStatus: LiveData<Event<ViewStatus>> = _viewStatus

    /**
     * 界面事件处理
     */
    private val _uiChange = MutableLiveData<Event<UIChange>>()
    val uiChange: LiveData<Event<UIChange>> = _uiChange

    /**
     * 吐司一条信息
     * @param msg String
     */
    fun toast(msg: String) {
        _uiChange.value = Event(UIChange.ToastEvent(msg))
    }

    /**
     * 结束界面
     */
    fun finish() {
        _uiChange.value = Event(UIChange.FinishEvent)
    }

    /**
     * 界面跳转
     * @param clzz Class<out AppCompatActivity> 目标Activity
     * @param isFinished Boolean    是否结束当前界面
     * @param params Array<out Pair<String, Any?>> 传递参数
     */
    fun startActivity(clzz: Class<out AppCompatActivity>,
                      isFinished: Boolean = false,
                      vararg params: Pair<String, Any?>){

        _uiChange.value = Event(UIChange.IntentEvent(clzz, isFinished, params))

    }

    override fun onCleared() {
        cancel()
        super.onCleared()
    }
}