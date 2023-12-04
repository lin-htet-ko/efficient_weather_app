package com.linhtetko.network.manager

import com.linhtetko.network.api.base.ApiConstant
import com.linhtetko.network.api.base.WeatherApi
import com.linhtetko.network.api.impl.RetrofitApiManager
import com.linhtetko.network.api.impl.RetrofitWeatherApi
import com.linhtetko.network.interceptor.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object ApiManagerImpl: ApiManager {

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val apiInterceptor: ApiKeyInterceptor = ApiKeyInterceptor()

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private val apiClient: RetrofitWeatherApi =
        Retrofit.Builder().baseUrl(ApiConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RetrofitWeatherApi::class.java)

    override val weatherApi: WeatherApi = RetrofitApiManager(apiClient)

}