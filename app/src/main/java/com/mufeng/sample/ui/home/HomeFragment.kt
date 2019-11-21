package com.mufeng.sample.ui.home

import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.mufeng.mvvmlib.basic.eventObserver
import com.mufeng.mvvmlib.basic.view.BaseVMFragment
import com.mufeng.mvvmlib.binding.setImageUri
import com.mufeng.mvvmlib.ext.loge
import com.mufeng.mvvmlib.widget.State
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

        binding.viewModel = viewModel

        bgaBanner.setAdapter { banner, itemView, model, position ->
            if (itemView is ImageView) {
                itemView.setImageUri(model as String)
            }
        }

        refreshLayout.setOnRefreshListener { viewModel.refreshArticleData() }
        adapter = HomeAdapter(viewModel)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

    }

    override fun initData() {
    }

    override fun startObserve() {
        super.startObserve()
        viewModel.bannerLiveData.observe(this) {
            val urls = arrayListOf<String>()
            val tips = arrayListOf<String>()
            it.forEach {  banner ->
                urls.add(banner.url!!)
                tips.add(banner.title!!)
            }
            bgaBanner.setData(urls, tips)
        }

        viewModel.homeUIModel.eventObserver(this) {
            when (it) {
                HomeViewModel.HomeUIModel.LOADING -> statefulLayout.state = State.Loading
                HomeViewModel.HomeUIModel.EMPTY_DATA -> statefulLayout.state = State.Empty
                HomeViewModel.HomeUIModel.REFRESH_SUCCESS -> {
                    refreshLayout.isRefreshing = false
                    statefulLayout.state = State.Success
                }
                else -> statefulLayout.state = State.Success
            }
        }

        viewModel.homeArticleData.observe(this){
            adapter.submitList(it)
        }
    }

}