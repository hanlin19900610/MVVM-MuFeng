package com.mufeng.sample.db.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/8 10:00
 * @描述
 */
@Entity
class Navigation (
    @PrimaryKey
    val cid: Int? = 0,
    val name: String? = "",
    @Ignore
    val articles: List<Article>? = emptyList()
)