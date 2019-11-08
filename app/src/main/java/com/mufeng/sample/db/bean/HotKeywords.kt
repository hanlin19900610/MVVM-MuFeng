package com.mufeng.sample.db.bean


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 搜索热词
 * @property id Int?
 * @property link String?
 * @property name String?
 * @property order Int?
 * @property visible Int?
 * @constructor
 */
@Entity
@JsonClass(generateAdapter = true)
data class HotKeywords(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "link")
    val link: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "order")
    val order: Int? = 0,
    @Json(name = "visible")
    val visible: Int? = 0
)