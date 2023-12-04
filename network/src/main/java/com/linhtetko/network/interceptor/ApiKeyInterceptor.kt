package com.linhtetko.network.interceptor

import com.linhtetko.network.api.base.ApiConstant
import com.linhtetko.network.utils.NativeStore
import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor(
    private val nativeStore: NativeStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .apply { addHeader(ApiConstant.Query.KEY, nativeStore.apiKeyFromJNI()) }.build()
        )
    }
}