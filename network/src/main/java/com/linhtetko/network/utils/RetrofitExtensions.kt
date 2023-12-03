package com.linhtetko.network.utils

import kotlinx.coroutines.flow.flowOf
import retrofit2.Response

internal fun <T> Response<T>.toFlow() = flowOf(this)

internal fun <T> Response<T>.handle(): Result<T?> {
    return when (code()) {
        in 200..299 -> Result.success(body())
        in 400..499 -> Result.failure(ClientException(message()))
        in 500..599 -> Result.failure(ServerException(message()))
        else -> Result.failure(IdleException(message()))
    }
}