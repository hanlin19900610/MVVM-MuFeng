package com.mufeng.sample.db.bean

/**
 * PageBean
 * @param T
 * @property curPage Int?
 * @property datas List<T>?
 * @property offset Int?
 * @property over Boolean?
 * @property pageCount Int?
 * @property size Int?
 * @property total Int?
 * @constructor
 */
class PageBean<T>(
    val curPage: Int? = 1,
    val datas: List<T>? = emptyList(),
    val offset: Int? = 0,
    val over: Boolean? = false,
    val pageCount: Int? = 0,
    val size: Int? = 0,
    val total: Int? = 0
)