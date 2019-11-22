package com.mufeng.sample.http

import com.mufeng.sample.db.bean.Article
import com.mufeng.sample.db.bean.Banner
import com.mufeng.sample.db.bean.BaseBean
import com.mufeng.sample.db.bean.PageBean
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author MuFeng-T
 * @createTime 2019-10-17
 * @details
 */
interface ApiService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    /**
     * 获取首页Banner列表
     * @return BaseBean<List<Banner>>
     */
    @GET("/banner/json")
    suspend fun getBanners(): BaseBean<List<Banner>>

    /**
     * 获取首页文章列表
     * @param page Int
     * @return BaseBean<List<Article>>
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): BaseBean<PageBean<Article>>

    /**
     * 获取首页置顶文章列表
     * @return BaseBean<List<Article>>
     */
    @GET("/article/top/json")
    suspend fun getTopArticles(): BaseBean<List<Article>>
}