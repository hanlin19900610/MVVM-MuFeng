package com.mufeng.sample.db.bean


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 体系列表
 * @property children List<Any?>?
 * @property courseId Int?
 * @property id Int?
 * @property name String?
 * @property order Int?
 * @property parentChapterId Int?
 * @property userControlSetTop Boolean?
 * @property visible Int?
 * @constructor
 */
@Entity
@JsonClass(generateAdapter = true)
data class ProjectChapter(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "courseId")
    val courseId: Int? = 0,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "order")
    val order: Int? = 0,
    @Json(name = "parentChapterId")
    val parentChapterId: Int? = 0,
    @Json(name = "userControlSetTop")
    val userControlSetTop: Boolean? = false,
    @Json(name = "visible")
    val visible: Int? = 0,

    @Ignore
    @Json(name = "children")
    val children: List<ProjectChapter?>? = listOf()
)