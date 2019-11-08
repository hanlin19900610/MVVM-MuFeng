package com.mufeng.sample.http

/**
 * @author MuFeng-T
 * @createTime 2019-10-17
 * @details
 */
sealed class Result
data class Success<out T>(val data: T) : Result()
data class Error(val httpError: HttpError) : Result()
