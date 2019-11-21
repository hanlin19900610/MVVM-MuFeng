package com.mufeng.sample.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mufeng.sample.db.bean.LastRequestTime

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/18 17:35
 * @描述
 */
@Dao
interface LastRequestTimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lastRequestTime: LastRequestTime)

    @Query("SELECT * FROM last_request_time WHERE type = :type")
    fun getLastRequestTime(type: String): LiveData<LastRequestTime>
}