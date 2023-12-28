package com.factorial.common.ext

import android.util.Log
import retrofit2.Response

// fun to make network requests
class NetworkExt {

    companion object {

        suspend fun <T> safeRequest(
            execute: suspend () -> Response<T>,
        ): Result<T> {
            return try {
                val response = execute()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Result.success(body)
                } else {
                    Result.failure(
                        Throwable(
                            response.errorBody()?.string()?.ifEmpty { "Unknown error from server with status ${response.code()}" })
                    )
                }
            } catch (e: Throwable) {
                Result.failure(e)
            }
        }

    }

}