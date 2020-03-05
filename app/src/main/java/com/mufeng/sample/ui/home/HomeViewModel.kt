package com.mufeng.sample.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.mufeng.mvvmlib.basic.BaseViewModel
import com.mufeng.mvvmlib.utils.Preference
import com.mufeng.sample.db.bean.*
import com.mufeng.sample.db.database.AppDatabase

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/7 16:11
 * @描述
 */
class HomeViewModel : BaseViewModel() {

    private val repository by lazy { HomeRepository() }
    private val bannerDao by lazy { AppDatabase.getInstance().bannerDao() }
    private val homeArticleDao by lazy { AppDatabase.getInstance().homeArticleDao() }

    private var topNum by Preference("topNum",0)

    /**
     * 首页界面状态
     */
    private val _homeUIModel = MutableLiveData<HomeUIModel>()
    val homeUIModel: LiveData<HomeUIModel>
        get() = _homeUIModel

    init {
        _homeUIModel.value = HomeUIModel.LOADING
        getBanners()
    }

    val bannerLiveData = bannerDao.getBanners()

    val homeArticleData = homeArticleDao.getHomeArticles().toLiveData(
        Config(pageSize = 20, initialLoadSizeHint = 20 + topNum),
        boundaryCallback = object : PagedList.BoundaryCallback<HomeArticle>() {

            private var page = 0

            override fun onZeroItemsLoaded() {
                page = 0
                getHomeArticleData(page)
            }

            override fun onItemAtEndLoaded(itemAtEnd: HomeArticle) {
                page++
                getHomeArticleData(page)
            }

        })


    private fun getBanners() {

    }

    fun refreshArticleData(){
        getHomeArticleData(0)
    }

    private fun getHomeArticleData(page: Int) {

    }

    /**
     * 列表项点击事件
     * @param homeArticle HomeArticle
     */
    fun itemClick(homeArticle: HomeArticle){

    }

    /**
     * 标签的点击事件
     * @param tag Tag
     */
    fun tagClick(tag: Tag) {
        toast("${tag.name} 标签的点击事件")
    }

    fun bannerItemClick(banner: Banner){

    }

    fun collectClick(homeArticle: HomeArticle){
        toast("收藏点击事件")
    }

    enum class HomeUIModel{
        LOADING, EMPTY_DATA, REFRESH_SUCCESS, REFRESH_ERROR
    }

}