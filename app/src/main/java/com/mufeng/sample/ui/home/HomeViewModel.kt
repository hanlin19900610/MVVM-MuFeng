package com.mufeng.sample.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.mufeng.mvvmlib.basic.BaseViewModel
import com.mufeng.mvvmlib.basic.Event
import com.mufeng.mvvmlib.utils.toast
import com.mufeng.sample.db.bean.*
import com.mufeng.sample.db.database.AppDatabase
import com.mufeng.sample.http.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/7 16:11
 * @描述
 */
class HomeViewModel : BaseViewModel() {

    private val repository by lazy { HomeRepository() }
    private val bannerDao by lazy { AppDatabase.getInstance().bannerDao() }
    private val homeArticleDao by lazy { AppDatabase.getInstance().homeArticleDao() }

    /**
     * 首页界面状态
     */
    private val _homeUIModel = MutableLiveData<Event<HomeUIModel>>()
    val homeUIModel: LiveData<Event<HomeUIModel>>
        get() = _homeUIModel

    init {
        _homeUIModel.value = Event(HomeUIModel.LOADING)
        getBanners()
    }

    val bannerLiveData = bannerDao.getBanners()
    private val topNum = homeArticleDao.getTopArticleNum()
    val homeArticleData = homeArticleDao.getHomeArticles().toLiveData(
        Config(pageSize = 20, initialLoadSizeHint = 20 + topNum),
        boundaryCallback = object : PagedList.BoundaryCallback<HomeArticle>() {
            override fun onZeroItemsLoaded() {
                getHomeArticleData(0)
            }

            override fun onItemAtEndLoaded(itemAtEnd: HomeArticle) {
                val nextPageIndex = ((homeArticleDao.getTotalNum() - topNum) / 20) + 1
                getHomeArticleData(nextPageIndex)
            }
        })


    fun getBanners() {
        launch({
            val response = withContext(Dispatchers.IO) {
                repository.getBanners()
            }
            if (response is Result.Success) {
                withContext(Dispatchers.IO) {
                    response.data?.let { bannerDao.insert(it) }
                }
            } else if (response is Result.Error) {
                toast(response.exception.message!!)
            }
        }, { error ->
            error.printStackTrace()
        })
    }

    fun refreshArticleData(){
        getHomeArticleData(0)
    }

    private fun getHomeArticleData(page: Int) {
        launch({
            withContext(Dispatchers.IO) {
                val topRes = if (page == 0) repository.getHomeTopArticle() else null
                val res = repository.getHomeArticle(page)
                if (page == 0) {
                    val data = arrayListOf<Article>()
                    if (topRes is Result.Success) {
                        topRes.data?.map { it.top = 1 }
                        data.addAll(topRes.data!!)
                    }
                    if (res is Result.Success) {
                        data.addAll(res.data!!)
                    }
                    homeArticleDao.deleteAllHomeArticles()
                    homeArticleDao.insert(data.toHomeArticle())
                    if (data.size == 0) {
                        _homeUIModel.value = Event(HomeUIModel.EMPTY_DATA)
                    }else {
                        _homeUIModel.value = Event(HomeUIModel.REFRESH_SUCCESS)
                    }
                } else {
                    if (res is Result.Success) {
                        homeArticleDao.insert(res.data?.toHomeArticle()!!)
                    }
                }
            }

        }, {

        })
    }

    /**
     * 列表项点击事件
     * @param homeArticle HomeArticle
     */
    fun itemClick(homeArticle: HomeArticle){
        toast("点击事件")
    }

    /**
     * 标签的点击事件
     * @param tag Tag
     */
    fun tagClick(tag: Tag) {
        toast("${tag.name} 标签的点击事件")
    }

    fun collectClick(homeArticle: HomeArticle){
        toast("收藏点击事件")
    }

    enum class HomeUIModel{
        LOADING, EMPTY_DATA, REFRESH_SUCCESS, REFRESH_ERROR
    }

}