package com.mufeng.sample.http

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/1/17 10:50
 * @描述
 */
sealed class Errors : Throwable() {
    data class NetworkError(val code: Int = -1, val desc: String = "") : Errors()
    object EmptyInputError : Errors()
    object EmptyResultsError : Errors()
    object SingleError : Errors()
}