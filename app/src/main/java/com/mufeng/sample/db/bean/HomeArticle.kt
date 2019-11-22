package com.mufeng.sample.db.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
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
@Entity(tableName = "home_article")
@TypeConverters(TagConverters::class)
data class HomeArticle(
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
    var tags: List<Tag?>? = listOf(),
    var top: Int = 0 // 1: 置顶, 0: 不置顶
){
    var indexInSortResponse: Int = -1   // persistent layer index
}

fun List<Article>.toHomeArticle(): List<HomeArticle> {
    val homeArticles = arrayListOf<HomeArticle>()
    forEach {
        val homeArticle = HomeArticle(
            id = it.id,
            apkLink = it.apkLink,
            audit = it.audit,
            author = it.author,
            chapterId = it.chapterId,
            chapterName = it.chapterName,
            collect = it.collect,
            courseId = it.courseId,
            desc = it.desc,
            envelopePic = it.envelopePic,
            fresh = it.fresh,
            link = it.link,
            niceDate = it.niceDate,
            niceShareDate = it.niceShareDate,
            origin = it.origin,
            prefix = it.prefix,
            projectLink = it.projectLink,
            publishTime = it.publishTime,
            selfVisible = it.selfVisible,
            shareDate = it.shareDate,
            shareUser = it.shareUser,
            superChapterId = it.superChapterId,
            superChapterName = it.superChapterName,
            title = it.title,
            type = it.type,
            userId = it.userId,
            visible = it.visible,
            zan = it.zan,
            tags = it.tags,
            top = it.top
        )
        homeArticles.add(homeArticle)
    }
    return homeArticles
}