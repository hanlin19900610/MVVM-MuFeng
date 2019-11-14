package com.mufeng.sample.http

import com.mufeng.sample.db.bean.BaseBean

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/14 14:19
 * @描述
 */

fun BaseBean<*>.isSuccess() = errorCode == 0

fun BaseBean<*>.isNotLogin() = errorCode == -1001

