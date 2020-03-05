package com.mufeng.sample.ui.login

import androidx.lifecycle.MutableLiveData
import com.mufeng.mvvmlib.basic.BaseViewModel

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/1/18 11:02
 * @描述
 */
class LoginViewModel : BaseViewModel(){

    val username = MutableLiveData<String>()
    val pwd = MutableLiveData<String>()



}