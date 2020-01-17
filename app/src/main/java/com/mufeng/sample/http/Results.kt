package com.mufeng.sample.http

/**
 * @author MuFeng-T
 * @createTime 2019-10-17
 * @details
 */
sealed class Results<out T> {

    companion object {
        fun <T> success(result: T): Results<T> = Success(result)
        fun <T> failure(error: Throwable): Results<T> = Failure(error)
    }

    data class Failure(val error: Throwable) : Results<Nothing>()
    data class Success<out T>(val data: T) : Results<T>()
}
