package com.mufeng.sample.ui.home

import com.mufeng.sample.db.bean.Banner
import com.mufeng.sample.db.bean.HomeArticle

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/22 11:06
 * @描述
 */
data class MultipleItem(
    val banners: List<Banner> = emptyList(),
    val homeArticle: HomeArticle? = null
)