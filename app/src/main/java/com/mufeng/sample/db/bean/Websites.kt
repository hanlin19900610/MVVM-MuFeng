package com.mufeng.sample.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 常用网站
 * @property icon String?
 * @property id Int?
 * @property link String?
 * @property name String?
 * @property order Int?
 * @property visible Int?
 * @constructor
 */
@Entity
data class Websites(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = 0,
    val icon: String? = "",
    val link: String? = "",
    val name: String? = "",
    val order: Int? = 0,
    val visible: Int? = 0
)