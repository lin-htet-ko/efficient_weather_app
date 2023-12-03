package com.linhtetko.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.linhtetko.network.api.base.ApiConstant
import com.linhtetko.network.api.base.ApiManager
import com.linhtetko.network.api.impl.RetrofitApiManager
import com.linhtetko.network.api.impl.RetrofitWeatherApi
import com.linhtetko.network.interceptor.ApiKeyInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiModule {

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val apiInterceptor: ApiKeyInterceptor = ApiKeyInterceptor()

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private val apiClient: RetrofitWeatherApi =
        Retrofit.Builder().baseUrl(ApiConstant.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
            .create(RetrofitWeatherApi::class.java)

    val apiManager: ApiManager = RetrofitApiManager(apiClient)
}