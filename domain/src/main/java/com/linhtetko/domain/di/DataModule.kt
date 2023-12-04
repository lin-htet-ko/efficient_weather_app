package com.linhtetko.domain.di

import android.content.Context
import com.linhtetko.network.api.base.WeatherApi
import com.linhtetko.network.di.ApiModule
import com.linhtetko.network.manager.ApiManager
import com.linhtetko.persistance.di.LocalModule
import com.linhtetko.persistance.manager.DatabaseManager

class DataModule(context: Context) {

    val databaseManager: DatabaseManager = LocalModule.getManager(context)

    val apiManager: WeatherApi = ApiModule.apiManager.weatherApi
}