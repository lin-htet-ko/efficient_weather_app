package com.linhtetko.network.di

import com.linhtetko.network.api.base.ApiConstant
import com.linhtetko.network.api.base.WeatherApi
import com.linhtetko.network.api.impl.RetrofitApiManager
import com.linhtetko.network.api.impl.RetrofitWeatherApi
import com.linhtetko.network.interceptor.ApiKeyInterceptor
import com.linhtetko.network.manager.ApiManager
import com.linhtetko.network.manager.ApiManagerImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiModule {

    val apiManager: ApiManager = ApiManagerImpl
}