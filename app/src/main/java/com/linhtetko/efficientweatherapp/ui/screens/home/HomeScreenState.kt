package com.linhtetko.efficientweatherapp.ui.screens.home

import androidx.compose.runtime.Immutable
import com.linhtetko.efficientweatherapp.domain.vos.WeatherCardVO
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseState

@Immutable
data class HomeScreenState(
    val current: BaseState<WeatherCardVO>,
    val theNext5DaysForecasts: BaseState<List<WeatherCardVO>>
) {

    companion object {
        val dummy = HomeScreenState(
            current = BaseState(data = WeatherCardVO.dummy),
            theNext5DaysForecasts = BaseState(data = (0..5).map { WeatherCardVO.dummy }))
    }
}
