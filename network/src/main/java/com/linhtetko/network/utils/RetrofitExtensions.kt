package com.linhtetko.network.utils

import android.util.Log.d
import com.linhtetko.network.vos.ErrorVO
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response

internal fun <T> Response<T>.toFlow() = flowOf(this)

internal fun <T> Response<T>.handle(): Result<T?> {

    val error = JsonParser.parseError(message() ?: "{}")
    d("OKOK", errorBody()?.string()?.ifBlank { "{}" } ?: "{}")

    return when (code()) {
        in 200..299 -> Result.success(body())
        in 400..499 -> Result.failure(ClientException(error?.message ?: "Something Wrong"))
        in 500..599 -> Result.failure(ServerException(error?.message ?: "Something Wrong"))
        else -> Result.failure(IdleException(error?.message ?: "Something Wrong"))
    }
}