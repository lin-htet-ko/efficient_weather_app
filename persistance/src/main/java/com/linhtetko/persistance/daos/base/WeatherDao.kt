package com.linhtetko.persistance.daos.base

import com.linhtetko.persistance.entities.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface WeatherDao {

    suspend fun insert(weather: WeatherEntity)

    suspend fun insert(vararg weathers: WeatherEntity)

    fun getLastWeather(): Flow<WeatherEntity>

    fun getWeathersByType(type: Int, count: Int): Flow<List<WeatherEntity>>

}