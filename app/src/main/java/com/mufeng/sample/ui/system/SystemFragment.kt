package com.mufeng.sample.ui.system

import androidx.fragment.app.viewModels
import com.mufeng.mvvmlib.basic.view.BaseVMFragment
import com.mufeng.sample.R
import com.mufeng.sample.databinding.FragmentSystemBinding
import com.mufeng.sample.ui.main.MainViewModel

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/7 15:00
 * @描述
 */
class SystemFragment : BaseVMFragment<SystemViewModel, FragmentSystemBinding>(){
    override val viewModel: SystemViewModel by viewModels()
    override val layoutResId: Int = R.layout.fragment_system
    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun initData() {
    }

}