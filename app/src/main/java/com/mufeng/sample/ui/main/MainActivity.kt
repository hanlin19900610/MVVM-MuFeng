package com.mufeng.sample.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED
import com.mufeng.mvvmlib.basic.adapter.BaseViewPagerAdapter
import com.mufeng.mvvmlib.basic.view.BaseVMActivity
import com.mufeng.sample.R
import com.mufeng.sample.databinding.ActivityMainBinding
import com.mufeng.sample.ui.home.HomeFragment
import com.mufeng.sample.ui.mine.MineFragment
import com.mufeng.sample.ui.project.ProjectFragment
import com.mufeng.sample.ui.square.SquareFragment
import com.mufeng.sample.ui.system.SystemFragment


class MainActivity : BaseVMActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
    }

    override fun initData() {
        val fragments = arrayListOf<Fragment>()
        val homeFragment = HomeFragment()
        val projectFragment = ProjectFragment()
        val squareFragment = SquareFragment()
        val systemFragment = SystemFragment()
        val mineFragment = MineFragment()
        fragments.add(homeFragment)
        fragments.add(projectFragment)
        fragments.add(squareFragment)
        fragments.add(systemFragment)
        fragments.add(mineFragment)

        binding.viewPager.offscreenPageLimit = fragments.size

        val adapter = BaseViewPagerAdapter(supportFragmentManager, fragments)
        binding.viewPager.adapter = adapter
        binding.bottomNavigationViewEx.setupWithViewPager(binding.viewPager, true)

        binding.bottomNavigationViewEx.enableAnimation(false)
        binding.bottomNavigationViewEx.labelVisibilityMode = LABEL_VISIBILITY_LABELED
        binding.bottomNavigationViewEx.isItemHorizontalTranslationEnabled = false
    }
}
