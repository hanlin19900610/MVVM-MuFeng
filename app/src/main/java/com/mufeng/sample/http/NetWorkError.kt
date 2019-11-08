package com.mufeng.sample.http

import com.mufeng.mvvmlib.utils.toast
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import kotlinx.coroutines.CancellationException
import org.json.JSONException
import java.net.SocketTimeoutException

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/6 14:43
 * @描述
 */
/**
 * 处理请求层的错误,对可能的已知的错误进行处理
 */
fun handlingExceptions(e: Throwable) {
    when (e) {
        is CancellationException -> {
        }
        is SocketTimeoutException -> {
        }
        is JSONException, is JsonEncodingException, is JsonDataException -> {
        }
        else -> {
        }
    }
}

enum class HttpError(val code: Int, val errorMsg: String?) {
    LOGIN_INVALID(-1001, "登录失效, 请重新登录"),
}

/**
 * 处理响应层的错误
 */
fun handlingApiExceptions(e: HttpError) {
    when (e) {
        HttpError.LOGIN_INVALID -> {

        }
    }
}

/**
 * 处理HttpResponse
 * @param res
 * @param successBlock 成功
 * @param failureBlock 失败
 */
fun <T> handlingHttpResponse(
    res: Result,
    successBlock: (data: T) -> Unit,
    failureBlock: ((error: HttpError) -> Unit)? = null
) {
    when (res) {
        is Success<*> -> {
            successBlock.invoke(res.data as T)
        }
        is Error -> {
            failureBlock?.invoke(res.httpError) ?: defaultErrorBlock.invoke(res.httpError)
        }
    }
}


// 默认的处理方案
val defaultErrorBlock: (error: HttpError) -> Unit = { error ->
    toast(error.errorMsg ?: "${error.code}")
}


