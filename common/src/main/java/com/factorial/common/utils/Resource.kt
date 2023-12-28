package com.factorial.common.utils

import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null,
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(throwable: Throwable) : Resource<T>(error = throwable)
    class Loading<T> : Resource<T>()

    fun isInternetError() = error is UnknownHostException || error is SocketTimeoutException

    companion object {

        fun <T> Result<T>.toResource(): Resource<T> =
            fold(
                onSuccess = { Success(it) },
                onFailure = { Error(it) }
            )

    }

}