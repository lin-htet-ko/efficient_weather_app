package com.linhtetko.domain.repositories

import com.linhtetko.domain.vos.WeatherVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun addCurrentWeather(weather: WeatherVO)

    suspend fun addForecastWeathers(weathers: List<WeatherVO>)

    fun getCurrentWeather(): Flow<WeatherVO?>

    fun get5DaysForecastWeathers(): Flow<List<WeatherVO>>

    suspend fun requestCurrentWeather(
        scope: CoroutineScope,
        city: String,
        onSuccess: (WeatherVO?) -> Unit,
        onFailure: (String) -> Unit
    )

    suspend fun searchWeatherByCity(
        city: String,
        onSuccess: (WeatherVO?) -> Unit,
        onFailure: (String) -> Unit
    )

    suspend fun request5DaysForecastWeathers(
        scope: CoroutineScope,
        city: String,
        onSuccess: (List<WeatherVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}