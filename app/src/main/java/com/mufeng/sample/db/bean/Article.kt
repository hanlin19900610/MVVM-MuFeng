package com.mufeng.sample.db.bean

import androidx.room.*
import com.mufeng.sample.db.converters.TagConverters


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
@Entity(tableName = "article")
@TypeConverters(TagConverters::class)
data class Article(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = 0,
    var apkLink: String? = "",
    var audit: Int? = 0,
    var author: String? = "",
    var chapterId: Int? = 0,
    var chapterName: String? = "",
    var collect: Boolean? = false,
    var courseId: Int? = 0,
    var desc: String? = "",
    var envelopePic: String? = "",
    var fresh: Boolean? = false,
    var link: String? = "",
    var niceDate: String? = "",
    var niceShareDate: String? = "",
    var origin: String? = "",
    var prefix: String? = "",
    var projectLink: String? = "",
    var publishTime: Long? = 0,
    var selfVisible: Int? = 0,
    var shareDate: Long? = 0,
    var shareUser: String? = "",
    var superChapterId: Int? = 0,
    var superChapterName: String? = "",
    var title: String? = "",
    var type: Int? = 0,
    var userId: Int? = 0,
    var visible: Int? = 0,
    var zan: Int? = 0,
    var tags: List<Tag?>? = listOf()
)