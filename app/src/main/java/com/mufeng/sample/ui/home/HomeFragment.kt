package com.mufeng.sample.ui.home

import androidx.fragment.app.viewModels
import com.mufeng.mvvmlib.basic.view.BaseVMFragment
import com.mufeng.sample.R
import com.mufeng.sample.databinding.FragmentHomeBinding

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/7 14:53
 * @描述
 */
class HomeFragment : BaseVMFragment<HomeViewModel, FragmentHomeBinding>(){
    override val viewModel: HomeViewModel by viewModels()
    override val layoutResId: Int = R.layout.fragment_home
    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun initData() {
    }

}