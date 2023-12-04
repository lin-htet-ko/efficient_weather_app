package com.linhtetko.domain.utils.extensions

import android.util.Log.d

fun <T> Result<T>.handle(
    onSuccess: (T) -> Unit,
    onFailure: (String) -> Unit
) {
    onSuccess { onSuccess(it) }.onFailure {
        onFailure(it.localizedMessage ?: "Something Wrong")
    }
}