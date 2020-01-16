package com.mufeng.sample.db.bean


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


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
data class ProjectChapter(
    @PrimaryKey(autoGenerate = false)
    @SerializedName(value = "id")
    val id: Int? = 0,
    @SerializedName(value = "courseId")
    val courseId: Int? = 0,
    @SerializedName(value = "name")
    val name: String? = "",
    @SerializedName(value = "order")
    val order: Int? = 0,
    @SerializedName(value = "parentChapterId")
    val parentChapterId: Int? = 0,
    @SerializedName(value = "userControlSetTop")
    val userControlSetTop: Boolean? = false,
    @SerializedName(value = "visible")
    val visible: Int? = 0,

    @Ignore
    @SerializedName(value = "children")
    val children: List<ProjectChapter?>? = listOf()
)