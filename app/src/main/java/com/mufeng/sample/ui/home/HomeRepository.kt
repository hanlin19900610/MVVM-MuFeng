package com.mufeng.sample.ui.home

import com.mufeng.sample.db.bean.Article
import com.mufeng.sample.db.bean.Banner
import com.mufeng.sample.db.bean.PageBean
import com.mufeng.sample.http.BaseRepository
import com.mufeng.sample.http.Result
import com.mufeng.sample.http.WAHttpUtils

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/18 15:30
 * @描述
 */
class HomeRepository : BaseRepository() {

    private val service by lazy { WAHttpUtils.service }

    suspend fun getBanners() : Result<List<Banner>>{
        return executeResponse(service.getBanners())
    }

    suspend fun getHomeArticle(page: Int): Result<PageBean<Article>> {
        return executeResponse(service.getHomeArticles(page))
    }

    suspend fun getHomeTopArticle(): Result<List<Article>> =
        executeResponse(service.getTopArticles())

}