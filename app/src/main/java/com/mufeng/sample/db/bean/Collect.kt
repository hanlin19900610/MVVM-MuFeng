package com.mufeng.sample.db.bean


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Collect(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "author")
    val author: String? = "",
    @Json(name = "chapterId")
    val chapterId: Int? = 0,
    @Json(name = "chapterName")
    val chapterName: String? = "",
    @Json(name = "courseId")
    val courseId: Int? = 0,
    @Json(name = "desc")
    val desc: String? = "",
    @Json(name = "envelopePic")
    val envelopePic: String? = "",
    @Json(name = "link")
    val link: String? = "",
    @Json(name = "niceDate")
    val niceDate: String? = "",
    @Json(name = "origin")
    val origin: String? = "",
    @Json(name = "originId")
    val originId: Int? = 0,
    @Json(name = "publishTime")
    val publishTime: Long? = 0,
    @Json(name = "title")
    val title: String? = "",
    @Json(name = "userId")
    val userId: Int? = 0,
    @Json(name = "visible")
    val visible: Int? = 0,
    @Json(name = "zan")
    val zan: Int? = 0
)