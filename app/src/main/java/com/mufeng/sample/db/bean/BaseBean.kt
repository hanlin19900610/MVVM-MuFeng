package com.mufeng.sample.db.bean

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/7 16:52
 * @描述
 */
class BaseBean<out T>(
    val data: T? = null,
    val errorCode: Int = 0,
    val errorMsg: String = ""
)