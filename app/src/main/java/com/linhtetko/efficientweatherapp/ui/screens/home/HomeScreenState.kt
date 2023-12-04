package com.linhtetko.efficientweatherapp.ui.screens.home

import androidx.compose.runtime.Immutable
import com.linhtetko.efficientweatherapp.ui.screens.base.BaseState
import com.linhtetko.efficientweatherapp.ui.vos.WeatherUiVO

@Immutable
data class HomeScreenState(
    val current: BaseState<WeatherUiVO>,
    val theNext5DaysForecasts: BaseState<List<WeatherUiVO>>,
) {

    companion object {
        val dummy = HomeScreenState(
            current = BaseState(data = WeatherUiVO.dummy),
            theNext5DaysForecasts = BaseState(data = (0..5).map { WeatherUiVO.dummy }),
        )
    }
}
