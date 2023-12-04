package com.linhtetko.network.api.base

import com.linhtetko.network.responses.BaseResponse

interface WeatherApi {

    suspend fun getWeatherByCity(city: String): Result<BaseResponse?>

    suspend fun searchWeatherByCity(city: String): Result<BaseResponse?>

    suspend fun getForecastedDayWeather(dayCount: Int, city: String): Result<BaseResponse?>

}