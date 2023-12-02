package com.linhtetko.efficientweatherapp.domain.vos

import androidx.compose.runtime.Immutable

@Immutable
data class WeatherCardVO(
    val day: String,
    val date: String,
    val time: String,
    val temp: String,
    val status: String,
    val statusIcon: String,
    val windySpeed: String,
    val uv: String,
    val rain: String,
    val location: String
) {

    companion object {
        val dummy = WeatherCardVO(
            day = "Monday",
            date = "01/01/2023",
            time = "05:00 PM",
            temp = "30 C",
            status = "Cloudy",
            statusIcon = "https://cdn2.iconfinder.com/data/icons/weather-flat-14/64/weather02-512.png",
            windySpeed = "6.1 kmp",
            uv = "70 %",
            rain = "6.1 kmp",
            location = "Yangon"
        )
    }
}
