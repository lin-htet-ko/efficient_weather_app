package com.linhtetko.efficientweatherapp.ui.screens.search

import com.linhtetko.efficientweatherapp.domain.vos.WeatherCardVO

data class SearchScreenState(
    val weather: WeatherCardVO
){

    companion object{
        val dummy = SearchScreenState(WeatherCardVO.dummy)
    }
}
