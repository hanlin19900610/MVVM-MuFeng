package com.mufeng.sample.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.mufeng.sample.db.bean.Article
import com.mufeng.sample.db.bean.HomeArticle

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/7 17:48
 * @描述
 */
@Dao
interface HomeArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: HomeArticle)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: List<HomeArticle>)

    @Update
    fun update(article: HomeArticle)

    @Delete
    fun delete(article: HomeArticle)

    /**
     * 查询所有文章列表
     */
    @Query("SELECT * FROM home_article")
    fun getAllArticles(): DataSource.Factory<Int, HomeArticle>

    /**
     * 获取首页文章列表数据
     */
    @Query("SELECT * FROM home_article ORDER BY top DESC , zan DESC, publishTime DESC")
    fun getHomeArticles(): DataSource.Factory<Int, HomeArticle>

    @Query("DELETE FROM home_article")
    fun deleteAllHomeArticles()

    @Query("SELECT COUNT(*) FROM home_article ORDER BY id")
    fun getTotalNum(): Int

    @Query("SELECT COUNT(*) FROM home_article WHERE top = 1 ORDER BY id")
    fun getTopArticleNum(): Int

}