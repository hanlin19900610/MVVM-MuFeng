package com.mufeng.sample.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.mufeng.mvvmlib.basic.view.BaseVMActivity
import com.mufeng.mvvmlib.ext.widget.clickWithTrigger
import com.mufeng.mvvmlib.utils.Preference
import com.mufeng.sample.R
import com.mufeng.sample.databinding.ActivityLoginBinding

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/1/18 11:02
 * @描述
 */
class LoginActivity : BaseVMActivity<LoginViewModel, ActivityLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override val layoutResId: Int
        get() = R.layout.activity_login

    private var userName by Preference("name","没有名字")


    override fun initView(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        binding.get.clickWithTrigger {
            binding.tvName.text = userName
        }
    }

    override fun startObserve() {
        super.startObserve()
        viewModel.username.observe(this){
            userName = it
        }
    }

    override fun initData() {
    }
}