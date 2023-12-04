package com.linhtetko.domain.repositories

import com.linhtetko.domain.di.DataModule
import com.linhtetko.domain.utils.extensions.handle
import com.linhtetko.domain.vos.WeatherVO
import com.linhtetko.persistance.entities.WeatherEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class WeatherRepoImpl(
    private val domainModule: DataModule
) : WeatherRepository {

    private val weatherDao = domainModule.databaseManager.weatherDao()
    private val apiManager = domainModule.apiManager.weatherApi

    override suspend fun addCurrentWeather(weather: WeatherVO) {
        weatherDao.insert(weather.toEntity(type = WeatherEntity.TYPE_CURRENT))
    }

    override suspend fun addForecastWeathers(weathers: List<WeatherVO>) {
        val entities = weathers.map { it.toEntity(type = WeatherEntity.TYPE_FORECAST) }
        weatherDao.insert(*entities.toTypedArray())
    }

    override fun getCurrentWeather(): Flow<WeatherVO?> {
        return weatherDao.getLastWeather().map { WeatherVO.fromEntity(it) }
    }

    override fun get5DaysForecastWeathers(): Flow<List<WeatherVO>> {
        return weatherDao.getWeathersByType(type = WeatherEntity.TYPE_FORECAST, count = 5)
            .mapNotNull { it.mapNotNull { item -> WeatherVO.fromEntity(item) }.reversed() }
    }

    override suspend fun requestCurrentWeather(
        scope: CoroutineScope,
        city: String,
        onSuccess: (WeatherVO?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        apiManager.getWeatherByCity(city)
            .handle(
                onSuccess = {
                    if (it != null) {
                        scope.launch(Dispatchers.IO) {
                            weatherDao.insert(
                                WeatherVO.fromCurrentWeatherApi(it)
                                    .toEntity(type = WeatherEntity.TYPE_CURRENT)
                            )
                        }
                    }
                },
                onFailure = onFailure
            )
    }

    override suspend fun searchWeatherByCity(
        city: String,
        onSuccess: (WeatherVO?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        apiManager.searchWeatherByCity(city)
            .handle(
                onSuccess = {
                    if (it != null) {
                        onSuccess(WeatherVO.fromCurrentWeatherApi(it))
                    }
                },
                onFailure = onFailure
            )
    }

    override suspend fun request5DaysForecastWeathers(
        scope: CoroutineScope,
        city: String,
        onSuccess: (List<WeatherVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        apiManager.getForecastedDayWeather(dayCount = 6, city = city)
            .handle(
                onSuccess = {
                    scope.launch(Dispatchers.IO) {

                        val items = it?.forecast?.days?.map { item ->
                            WeatherVO.fromForecastWeatherApi(it.location?.name.orEmpty(), item)
                        }
                        val entities =
                            items?.map { item -> item.toEntity(type = WeatherEntity.TYPE_FORECAST) }
                                ?.toTypedArray()

                        if (!entities.isNullOrEmpty())
                            weatherDao.insert(*entities)

                        if (!items.isNullOrEmpty())
                            onSuccess(items)
                    }
                },
                onFailure = onFailure
            )
    }
}