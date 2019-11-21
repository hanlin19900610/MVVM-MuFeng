package com.mufeng.sample.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mufeng.sample.db.bean.Banner

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/18 15:39
 * @描述
 */
@Dao
interface BannerDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(banners: List<Banner>)

    @Query("SELECT * FROM banner")
    fun getBanners(): LiveData<List<Banner>>

    @Query("DELETE  FROM banner")
    fun delectAllBanner()

}