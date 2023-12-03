package com.linhtetko.network.api.base

import com.linhtetko.network.responses.BaseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface ApiManager {

    suspend fun getWeatherByCity(city: String): Flow<Result<BaseResponse?>>

    suspend fun searchWeatherByCity(city: String): Flow<Result<BaseResponse?>>

    suspend fun getForecastedDayWeather(dayCount: Int): Flow<Result<BaseResponse?>>

}