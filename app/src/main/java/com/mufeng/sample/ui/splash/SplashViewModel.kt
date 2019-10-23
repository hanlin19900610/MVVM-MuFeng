package com.mufeng.sample.ui.splash

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import com.mufeng.mvvmlib.basic.BaseViewModel
import com.mufeng.sample.ui.main.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * @author MuFeng-T
 * @createTime 2019-10-22
 * @details
 */
@ExperimentalCoroutinesApi
class SplashViewModel : BaseViewModel() {

    val time = MutableLiveData<String>()

    private val countDownTimer = object : CountDownTimer(3000,1000){
        override fun onFinish() {
            //跳转到首页
            startActivity(MainActivity::class.java, isFinished = true)
        }

        override fun onTick(p0: Long) {
            // 更改倒计时时间
            time.value = "${p0/1000}S"
        }
    }

    init {
        time.value = "3S"
        countDownTimer.start()
    }

    fun onSkip(){
        countDownTimer.cancel()
        startActivity(MainActivity::class.java, isFinished = true)
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer.cancel()
    }

}