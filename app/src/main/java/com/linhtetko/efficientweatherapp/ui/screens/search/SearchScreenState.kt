package com.linhtetko.efficientweatherapp.ui.screens.search

import com.linhtetko.efficientweatherapp.ui.vos.WeatherUiVO

data class SearchScreenState(
    val weather: WeatherUiVO
){

    companion object{
        val dummy = SearchScreenState(WeatherUiVO.dummy)
    }
}
