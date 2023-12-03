package com.linhtetko.network.api.impl

import com.linhtetko.network.api.base.ApiConstant
import com.linhtetko.network.responses.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RetrofitWeatherApi {

    @GET(ApiConstant.Route.GET_CITY_WEATHER)
    suspend fun getWeatherByCity(@Query(ApiConstant.Query.CITY) city: String): Response<BaseResponse>

    @GET(ApiConstant.Route.GET_SEARCH_BY_CITY)
    suspend fun searchWeatherByCity(@Query(ApiConstant.Query.CITY) city: String): Response<BaseResponse>

    @GET(ApiConstant.Route.GET_FORECAST_WEATHER)
    suspend fun getForecastedDayWeather(@Query(ApiConstant.Query.DAY_COUNT) dayCount: Int): Response<BaseResponse>

}