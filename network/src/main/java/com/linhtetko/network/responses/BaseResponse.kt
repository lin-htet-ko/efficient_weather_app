package com.linhtetko.network.responses

import com.linhtetko.network.vos.ForecastApiVO
import com.linhtetko.network.vos.LocationApiVO
import com.linhtetko.network.vos.WeatherConditionApiVO
import com.linhtetko.network.vos.WeatherStatusApiVO
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val location: LocationApiVO? = null,
    val current: WeatherStatusApiVO? = null,
    val forecast: ForecastApiVO? = null
)
