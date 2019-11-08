package com.mufeng.sample.ui.square

import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.mufeng.mvvmlib.basic.view.BaseVMFragment
import com.mufeng.sample.R
import com.mufeng.sample.databinding.FragmentProjectBinding
import com.mufeng.sample.databinding.FragmentSquareBinding
import com.mufeng.sample.ui.main.MainViewModel

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/7 15:00
 * @描述
 */
class SquareFragment : BaseVMFragment<SquareViewModel, FragmentSquareBinding>(){
    override val viewModel: SquareViewModel by viewModels()
    override val layoutResId: Int = R.layout.fragment_square
    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun initData() {
    }

}