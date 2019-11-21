package com.mufeng.sample.http

import com.mufeng.sample.db.bean.BaseBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.lang.Exception

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/14 14:26
 * @描述
 */
open class BaseRepository {
    suspend fun <T : Any> executeResponse(
        response: BaseBean<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<T> {
        return coroutineScope {
            try {
                when {
                    response.isSuccess() -> {
                        successBlock?.let { it() }
                        Result.Success(response.data)
                    }
                    response.isNotLogin() -> {
                        //登录失效
                        errorBlock?.let { it() }
                        Result.Error(InvalidLoginError())
                    }
                    else -> {
                        errorBlock?.let { it() }
                        Result.Error(IOException(response.errorMsg))
                    }
                }
            } catch (e: Exception) {
                errorBlock?.let { it() }
                Result.Error(IOException(response.errorMsg))
            }
        }
    }

}