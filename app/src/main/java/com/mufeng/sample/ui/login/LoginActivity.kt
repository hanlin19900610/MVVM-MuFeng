package com.mufeng.sample.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import com.mufeng.mvvmlib.basic.view.BaseVMActivity
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

    override fun initView(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
    }

    override fun initData() {
    }
}