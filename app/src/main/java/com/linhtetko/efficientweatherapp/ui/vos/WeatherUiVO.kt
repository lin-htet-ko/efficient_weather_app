package com.linhtetko.efficientweatherapp.ui.vos

import androidx.compose.runtime.Immutable
import com.linhtetko.domain.vos.WeatherVO

@Immutable
data class WeatherUiVO(
    val day: String,
    val date: String,
    val time: String,
    val tempC: Double,
    val tempF: Double,
    val status: String,
    val statusIcon: String,
    val windySpeedKph: Double,
    val windySpeedMph: Double,
    val uv: String,
    val cloud: Int,
    val location: String
) {

    companion object {
        val dummy = WeatherUiVO(
            day = "Monday",
            date = "01/01/2023",
            time = "05:00 PM",
            tempF = 30.0,
            tempC = 30.0,
            status = "Cloudy",
            statusIcon = "https://cdn2.iconfinder.com/data/icons/weather-flat-14/64/weather02-512.png",
            windySpeedMph = 6.1,
            windySpeedKph = 6.1,
            uv = 70.0.toString(),
            cloud = 10,
            location = "Yangon"
        )

        fun fromDomain(weather: WeatherVO): WeatherUiVO{
            return WeatherUiVO(
                day = weather.day,
                date = weather.date,
                time = weather.time,
                tempC = weather.tempC,
                tempF = weather.tempF,
                status = weather.status,
                statusIcon = weather.statusIcon,
                windySpeedKph = weather.windySpeedKph,
                windySpeedMph = weather.windySpeedMph,
                uv = weather.uv.toString(),
                cloud = weather.cloud,
                location = weather.location,
            )
        }
    }
}
