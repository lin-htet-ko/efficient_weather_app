package com.linhtetko.network.interceptor

import com.linhtetko.network.api.base.ApiConstant
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .apply { addHeader(ApiConstant.Query.KEY, ApiConstant.API_KEY) }.build()
        )
    }
}