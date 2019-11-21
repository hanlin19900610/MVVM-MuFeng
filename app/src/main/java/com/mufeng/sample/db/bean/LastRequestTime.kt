package com.mufeng.sample.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/18 17:31
 * @描述
 */
@Entity(tableName = "last_request_time")
data class LastRequestTime(
    @PrimaryKey
    val type: String,
    val timestamp: Long
)

/**
 * 首页文章列表最后请求时间
 */
const val HOME_ARTICLE_REQUEST_TIME = "home_article_request_time"
/**
 * 首页Banner最后请求时间
 */
const val HOME_BANNER_REQUEST_TIME = "home_banner_request_time"