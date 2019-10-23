package com.mufeng.mvvmlib.basic

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.coroutines.*

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/14 22:20
 * @描述
 */



open class BaseViewModel : ViewModel(), LifecycleObserver {

    /**
     * 错误处理
     */
    private val _exception: MutableLiveData<Throwable> = MutableLiveData()
    val exception: LiveData<Throwable>
        get() = _exception

    /**
     * 网络加载状态
     */
    private val _viewStatus = MutableLiveData<ViewStatus>()
    val viewStatus: LiveData<ViewStatus>
        get() = _viewStatus

    /**
     * 界面事件处理
     */
    private val _uiChange = MutableLiveData<UIChange>()
    val uiChange: LiveData<UIChange>
        get() = _uiChange

    /**
     * 吐司一条信息
     * @param msg String
     */
    fun toast(msg: String) {
        _uiChange.value = UIChange.ToastEvent(msg)
    }

    /**
     * 结束界面
     */
    fun finish() {
        _uiChange.value = UIChange.FinishEvent
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

        _uiChange.value = UIChange.IntentEvent(clzz, isFinished, params)

    }


    private fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }

    fun launch(tryBlock: suspend CoroutineScope.() -> Unit) {
        launchOnUI {
            tryCatch(tryBlock, {}, {}, true)
        }
    }


    fun launchOnUITryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
        finallyBlock: suspend CoroutineScope.() -> Unit,
        handleCancellationExceptionManually: Boolean
    ) {
        launchOnUI {
            tryCatch(tryBlock, catchBlock, finallyBlock, handleCancellationExceptionManually)
        }
    }

    fun launchOnUITryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        handleCancellationExceptionManually: Boolean = false
    ) {
        launchOnUI {
            tryCatch(tryBlock, {}, {}, handleCancellationExceptionManually)
        }
    }


    private suspend fun tryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
        finallyBlock: suspend CoroutineScope.() -> Unit,
        handleCancellationExceptionManually: Boolean = false
    ) {
        coroutineScope {
            try {
                tryBlock()
            } catch (e: Throwable) {
                if (e !is CancellationException || handleCancellationExceptionManually) {
                    _exception.value = e
                    _viewStatus.value = ViewStatus.ERROR
                    catchBlock(e)
                } else {
                    throw e
                }
            } finally {
                finallyBlock()
            }
        }
    }

}