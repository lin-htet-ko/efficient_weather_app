package com.linhtetko.efficientweatherapp.ui.screens.home

import android.content.Context
import android.location.Geocoder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.linhtetko.domain.repositories.WeatherRepository
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseState
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseViewModel
import com.linhtetko.efficientweatherapp.ui.vos.WeatherUiVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepo: WeatherRepository,
    private val locationProvider: FusedLocationProviderClient
) : BaseViewModel() {

    private var _currentLocationWeather by mutableStateOf<BaseState<WeatherUiVO>>(BaseState())
    private var _theNext5WeatherForecast by mutableStateOf(BaseState(data = listOf<WeatherUiVO>()))

    var state by mutableStateOf(
        HomeScreenState(
            current = _currentLocationWeather,
            theNext5DaysForecasts = _theNext5WeatherForecast,
        )
    )
        private set

    init {
        getCacheCurrentWeather()
        getCacheForecastWeathers()
    }

    private fun getCacheCurrentWeather() {

        _currentLocationWeather = _currentLocationWeather.toLoadingState()
        state = state.copy(current = _currentLocationWeather)

        viewModelScope.launch(Dispatchers.IO) {
            weatherRepo.getCurrentWeather()
                .flowOn(Dispatchers.IO)
                .catch {
                    withContext(Dispatchers.Main) {
                        _currentLocationWeather = _currentLocationWeather.toErrorState(
                            it.localizedMessage ?: "Something Wrong"
                        )
                        state = state.copy(current = _currentLocationWeather)
                    }
                }
                .collect {
                    withContext(Dispatchers.Main) {
                        if (it != null) {
                            _currentLocationWeather =
                                _currentLocationWeather.toDataState(data = WeatherUiVO.fromDomain(it))
                            state = state.copy(current = _currentLocationWeather)
                        }
                    }
                }
        }
    }

    private fun getCacheForecastWeathers() {

        _theNext5WeatherForecast = _theNext5WeatherForecast.toLoadingState()
        state = state.copy(theNext5DaysForecasts = _theNext5WeatherForecast)

        viewModelScope.launch(Dispatchers.IO) {
            weatherRepo.get5DaysForecastWeathers()
                .flowOn(Dispatchers.IO)
                .catch {
                    withContext(Dispatchers.Main) {
                        _theNext5WeatherForecast = _theNext5WeatherForecast.toErrorState(
                            it.localizedMessage ?: "Something Wrong"
                        )
                        state = state.copy(theNext5DaysForecasts = _theNext5WeatherForecast)
                    }
                }
                .collect {
                    withContext(Dispatchers.Main) {
                        val items = it.map { item ->
                            WeatherUiVO.fromDomain(item)
                        }
                        _theNext5WeatherForecast =
                            _theNext5WeatherForecast.toDataState(items)
                        state = state.copy(theNext5DaysForecasts = _theNext5WeatherForecast)
                    }
                }
        }
    }

    fun getCurrentWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                weatherRepo.requestCurrentWeather(scope = viewModelScope, city = city,
                    onSuccess = {},
                    onFailure = {
                        viewModelScope.launch(Dispatchers.Main) {
                            state = state.copy(current = _currentLocationWeather.toErrorState(it))
                        }
                    }
                )
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    state = state.copy(
                        current = _currentLocationWeather.toErrorState(
                            e.localizedMessage ?: "Something Wrong"
                        )
                    )
                }
            }
        }
    }

    fun getTheNext5DaysWeatherForecast(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                weatherRepo.request5DaysForecastWeathers(
                    city = city,
                    scope = viewModelScope,
                    onSuccess = {},
                    onFailure = {
                        viewModelScope.launch(Dispatchers.Main) {
                            state = state.copy(
                                theNext5DaysForecasts = _theNext5WeatherForecast.toErrorState(it)
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    state = state.copy(
                        theNext5DaysForecasts = _theNext5WeatherForecast.toErrorState(
                            e.localizedMessage ?: "Something Wrong"
                        )
                    )
                }
            }
        }

    }

    @SuppressWarnings("MissingPermission")
    fun getCity(
        context: Context,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val geo = Geocoder(context, Locale.getDefault())

        locationProvider.lastLocation
            .addOnSuccessListener {
                viewModelScope.launch(Dispatchers.IO) {
                    val addresses = geo.getFromLocation(it.latitude, it.longitude, 1)
                    val city = addresses?.getOrNull(0)?.locality
                    if (city.isNullOrEmpty()) {
                        onSuccess(city!!)
                    }
                }
            }
            .addOnFailureListener { onFailure(it.localizedMessage ?: "Something Wrong") }
    }
}