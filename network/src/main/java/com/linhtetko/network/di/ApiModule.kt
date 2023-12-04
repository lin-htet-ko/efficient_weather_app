package com.linhtetko.network.di

import com.linhtetko.network.api.base.WeatherApi
import com.linhtetko.network.manager.ApiManager
import com.linhtetko.network.manager.ApiManagerImpl

object ApiModule {

    val apiManager: ApiManager = ApiManagerImpl

}