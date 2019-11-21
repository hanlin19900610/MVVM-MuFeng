package com.mufeng.sample.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mufeng.sample.app.App
import com.mufeng.sample.db.bean.Article
import com.mufeng.sample.db.bean.Banner
import com.mufeng.sample.db.bean.HomeArticle
import com.mufeng.sample.db.bean.LastRequestTime
import com.mufeng.sample.db.dao.ArticleDao
import com.mufeng.sample.db.dao.BannerDao
import com.mufeng.sample.db.dao.HomeArticleDao
import com.mufeng.sample.db.dao.LastRequestTimeDao

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/18 15:45
 * @描述
 */
@Database(
    entities = [Banner::class, HomeArticle::class, Article::class, LastRequestTime::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bannerDao(): BannerDao
    abstract fun homeArticleDao(): HomeArticleDao
    abstract fun articleDao(): ArticleDao
    abstract fun lastRequestTimeDao(): LastRequestTimeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        App.CONTEXT.applicationContext,
                        AppDatabase::class.java,
                        "mufeng_wan_android"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}