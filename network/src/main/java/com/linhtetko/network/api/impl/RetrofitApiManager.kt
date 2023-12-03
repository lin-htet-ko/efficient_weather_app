package com.linhtetko.network.api.impl

import com.linhtetko.network.api.base.ApiManager
import com.linhtetko.network.responses.BaseResponse
import com.linhtetko.network.utils.handle
import com.linhtetko.network.utils.toFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RetrofitApiManager(
    private val api: RetrofitWeatherApi
) : ApiManager {

    override suspend fun getWeatherByCity(city: String): Flow<Result<BaseResponse?>> {
        return api.getWeatherByCity(city).toFlow().map { it.handle() }
    }

    override suspend fun searchWeatherByCity(city: String): Flow<Result<BaseResponse?>> {
        return api.searchWeatherByCity(city).toFlow().map { it.handle() }
    }

    override suspend fun getForecastedDayWeather(dayCount: Int): Flow<Result<BaseResponse?>> {
        return api.getForecastedDayWeather(dayCount).toFlow().map { it.handle() }
    }
}