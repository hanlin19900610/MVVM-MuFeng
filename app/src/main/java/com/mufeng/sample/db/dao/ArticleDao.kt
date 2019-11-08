package com.mufeng.sample.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mufeng.sample.db.bean.Article

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/7 17:48
 * @描述
 */
@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: List<Article>)

    @Update
    fun update(article: Article)

    @Delete
    fun delete(article: Article)

    /**
     * 查询所有文章列表
     */
    @Query("SELECT * FROM article")
    fun getAllArticles()

    /**
     * 获取首页文章列表数据
     */
    @Query("SELECT * FROM Article ORDER BY fresh DESC , zan DESC, publishTime DESC")
    fun getHomeArticles(): LiveData<List<Article>>

    /**
     * 获取指定chapterId 的文章列表
     * @param chapterId Int
     */
    @Query("SELECT * FROM Article WHERE chapterId = :chapterId ORDER BY fresh DESC , zan DESC, publishTime DESC")
    fun getArticlesWithChapterId(chapterId: Int)

}