package com.mufeng.sample.db.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


/**
 * 文章
 * @property apkLink String?
 * @property audit Int?
 * @property author String?
 * @property chapterId Int?
 * @property chapterName String?
 * @property collect Boolean?
 * @property courseId Int?
 * @property desc String?
 * @property envelopePic String?
 * @property fresh Boolean?
 * @property id Int?
 * @property link String?
 * @property niceDate String?
 * @property niceShareDate String?
 * @property origin String?
 * @property prefix String?
 * @property projectLink String?
 * @property publishTime Long?
 * @property selfVisible Int?
 * @property shareDate Long?
 * @property shareUser String?
 * @property superChapterId Int?
 * @property superChapterName String?
 * @property tags List<Tag?>?
 * @property title String?
 * @property type Int?
 * @property userId Int?
 * @property visible Int?
 * @property zan Int?
 * @constructor
 */
@Entity
data class Article(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = 0,
    val apkLink: String? = "",
    val audit: Int? = 0,
    val author: String? = "",
    val chapterId: Int? = 0,
    val chapterName: String? = "",
    val collect: Boolean? = false,
    val courseId: Int? = 0,
    val desc: String? = "",
    val envelopePic: String? = "",
    val fresh: Boolean? = false,
    val link: String? = "",
    val niceDate: String? = "",
    val niceShareDate: String? = "",
    val origin: String? = "",
    val prefix: String? = "",
    val projectLink: String? = "",
    val publishTime: Long? = 0,
    val selfVisible: Int? = 0,
    val shareDate: Long? = 0,
    val shareUser: String? = "",
    val superChapterId: Int? = 0,
    val superChapterName: String? = "",
    val title: String? = "",
    val type: Int? = 0,
    val userId: Int? = 0,
    val visible: Int? = 0,
    val zan: Int? = 0,

    var isHome: Boolean? = false,

    @Ignore
    val tags: List<Tag?>? = listOf()
)