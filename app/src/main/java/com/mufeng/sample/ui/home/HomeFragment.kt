package com.mufeng.sample.ui.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.mufeng.mvvmlib.basic.view.BaseVMFragment
import com.mufeng.mvvmlib.ext.widget.removeAllAnimation
import com.mufeng.mvvmlib.utils.Preference
import com.mufeng.mvvmlib.widget.State
import com.mufeng.mvvmlib.widget.StatefulLayout
import com.mufeng.sample.R
import com.mufeng.sample.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/7 14:53
 * @描述
 */
class HomeFragment : BaseVMFragment<HomeViewModel, FragmentHomeBinding>(){


    override val viewModel: HomeViewModel by viewModels()
    override val layoutResId: Int = R.layout.fragment_home

    private lateinit var adapter: HomeAdapter
    override fun initView() {
        statefulLayout = binding.statefulLayout
        binding.viewModel = viewModel

        refreshLayout.setOnRefreshListener { viewModel.refreshArticleData() }
        adapter = HomeAdapter(viewModel)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        recyclerView.removeAllAnimation()

    }

    override fun initData() {
    }

    override fun startObserve() {
        super.startObserve()
        viewModel.bannerLiveData.observe(this) {
            adapter.setHeaderData(it)
        }

        viewModel.homeUIModel.observe(this) {
            when (it) {
                HomeViewModel.HomeUIModel.LOADING -> refreshLayout.isRefreshing = true
                HomeViewModel.HomeUIModel.EMPTY_DATA -> {
                    refreshLayout.isRefreshing = false
                    statefulLayout?.state = State.Empty
                }
                HomeViewModel.HomeUIModel.REFRESH_SUCCESS -> {
                    refreshLayout.isRefreshing = false
                    statefulLayout?.state = State.Success
                }
                else -> refreshLayout.isRefreshing = false
            }
        }

        viewModel.homeArticleData.observe(this){
            statefulLayout?.state = State.Success
            refreshLayout.isRefreshing = false
            adapter.submitList(it)
        }
    }

}