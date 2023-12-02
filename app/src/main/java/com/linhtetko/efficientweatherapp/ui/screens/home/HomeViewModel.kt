package com.linhtetko.efficientweatherapp.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.linhtetko.efficientweatherapp.domain.vos.WeatherCardVO
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseState
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private var _currentLocationWeather by mutableStateOf<BaseState<WeatherCardVO>>(BaseState(data = null))
    private var _theNext5WeatherForecast by mutableStateOf(BaseState(data = listOf<WeatherCardVO>()))

    var state by mutableStateOf(
        HomeScreenState(
            current = _currentLocationWeather,
            theNext5DaysForecasts = _theNext5WeatherForecast
        )
    )
        private set

    fun getCurrentWeather() {

        _currentLocationWeather = _currentLocationWeather.toLoadingState()
        state = state.copy(current = _currentLocationWeather)

        viewModelScope.launch {
            delay(5000)

            _currentLocationWeather =
                _currentLocationWeather.toDataState(data = WeatherCardVO.dummy)
            state = state.copy(current = _currentLocationWeather)
        }

    }

    fun getTheNext5DaysWeatherForecast() {
        _theNext5WeatherForecast = _theNext5WeatherForecast.toLoadingState()
        state = state.copy(theNext5DaysForecasts = _theNext5WeatherForecast)

        viewModelScope.launch {
            delay(5000)

            _theNext5WeatherForecast =
                _theNext5WeatherForecast.toDataState((0..5).map { WeatherCardVO.dummy })
            state = state.copy(theNext5DaysForecasts = _theNext5WeatherForecast)
        }

    }
}