package com.mufeng.mvvmlib.basic

import java.io.IOException
import com.mufeng.mvvmlib.http.Result

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/14 22:21
 * @描述
 */
open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            Result.Error(IOException(errorMessage, e))
        }
    }

}