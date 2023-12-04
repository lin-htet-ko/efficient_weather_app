package com.linhtetko.network.api.impl

import com.linhtetko.network.api.base.WeatherApi
import com.linhtetko.network.responses.BaseResponse
import com.linhtetko.network.utils.handle

internal class RetrofitApiManager(
    private val api: RetrofitWeatherApi
) : WeatherApi {

    override suspend fun getWeatherByCity(city: String): Result<BaseResponse?> {
        return api.getWeatherByCity(city).handle()
    }

    override suspend fun searchWeatherByCity(city: String): Result<BaseResponse?> {
        return api.getWeatherByCity(city).handle()
    }

    override suspend fun getForecastedDayWeather(
        dayCount: Int,
        city: String
    ): Result<BaseResponse?> {
        return api.getForecastedDayWeather(dayCount, city).handle()
    }
}