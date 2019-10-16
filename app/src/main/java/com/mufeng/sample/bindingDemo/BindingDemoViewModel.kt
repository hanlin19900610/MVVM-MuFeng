package com.mufeng.sample.bindingDemo

import androidx.lifecycle.MutableLiveData
import com.mufeng.mvvmlib.basic.BaseViewModel
import com.mufeng.mvvmlib.binding.BindingCommand1
import timber.log.Timber

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/15 22:29
 * @描述
 */
class BindingDemoViewModel : BaseViewModel() {

    val aa = MutableLiveData<Boolean>()

    val checkedChangeCommand = object : BindingCommand1<Boolean> {
        override fun apply(t1: Boolean) {
            if (t1) {
                Timber.e("复选框被选中")
            } else {
                Timber.e("复选框反选")
            }
        }

    }



}