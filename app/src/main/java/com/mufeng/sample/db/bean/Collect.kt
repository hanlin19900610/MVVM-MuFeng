package com.mufeng.sample.db.bean


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Collect(
    @PrimaryKey(autoGenerate = false)
    @SerializedName(value = "id")
    val id: Int? = 0,
    @SerializedName(value = "author")
    val author: String? = "",
    @SerializedName(value = "chapterId")
    val chapterId: Int? = 0,
    @SerializedName(value = "chapterName")
    val chapterName: String? = "",
    @SerializedName(value = "courseId")
    val courseId: Int? = 0,
    @SerializedName(value = "desc")
    val desc: String? = "",
    @SerializedName(value = "envelopePic")
    val envelopePic: String? = "",
    @SerializedName(value = "link")
    val link: String? = "",
    @SerializedName(value = "niceDate")
    val niceDate: String? = "",
    @SerializedName(value = "origin")
    val origin: String? = "",
    @SerializedName(value = "originId")
    val originId: Int? = 0,
    @SerializedName(value = "publishTime")
    val publishTime: Long? = 0,
    @SerializedName(value = "title")
    val title: String? = "",
    @SerializedName(value = "userId")
    val userId: Int? = 0,
    @SerializedName(value = "visible")
    val visible: Int? = 0,
    @SerializedName(value = "zan")
    val zan: Int? = 0
)