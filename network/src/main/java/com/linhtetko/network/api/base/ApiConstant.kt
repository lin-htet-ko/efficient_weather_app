package com.linhtetko.network.api.base

object ApiConstant {
    
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