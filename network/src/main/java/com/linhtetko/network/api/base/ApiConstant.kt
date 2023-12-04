package com.linhtetko.network.api.base

object ApiConstant {

    const val BASE_URL = "https://api.weatherapi.com/v1/"
    const val API_KEY = "b58cabed85c14d8491e142059230112"

    object Route {

        const val GET_CITY_WEATHER = "current.json"
        const val GET_FORECAST_WEATHER = "forecast.json"

    }

    object Query{

        const val CITY = "q"
        const val KEY = "key"
        const val DAY_COUNT = "days"

    }
}