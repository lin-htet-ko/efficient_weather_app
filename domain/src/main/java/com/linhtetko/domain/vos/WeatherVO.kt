package com.linhtetko.domain.vos

import com.linhtetko.domain.utils.DateManager
import com.linhtetko.domain.utils.extensions.orInvalid
import com.linhtetko.network.responses.BaseResponse
import com.linhtetko.network.vos.ForecastDayItemApiVO
import com.linhtetko.persistance.entities.WeatherEntity


data class WeatherVO(
    val day: String,
    val date: String,
    val time: String,
    val tempC: Double,
    val tempF: Double,
    val status: String,
    val statusIcon: String,
    val windySpeedKph: Double,
    val windySpeedMph: Double,
    val uv: Double,
    val cloud: Int,
    val location: String
) {

    companion object {
        val dummy = WeatherVO(
            day = "Monday",
            date = "01/01/2023",
            time = "05:00 PM",
            tempC = 1.0,
            tempF = 2.0,
            status = "Cloudy",
            statusIcon = "https://cdn2.iconfinder.com/data/icons/weather-flat-14/64/weather02-512.png",
            windySpeedKph = 6.1,
            windySpeedMph = 6.1,
            uv = 70.0,
            cloud = 6,
            location = "Yangon"
        )

        fun fromEntity(weather: WeatherEntity?) = weather?.let {
            WeatherVO(
                day = weather.day,
                date = weather.date,
                time = weather.time,
                tempC = weather.tempC,
                tempF = weather.tempF,
                status = weather.status,
                statusIcon = weather.statusIcon,
                windySpeedKph = weather.windySpeedKph,
                windySpeedMph = weather.windySpeedMph,
                uv = weather.uv,
                cloud = weather.cloud,
                location = weather.location,
            )
        }

        fun fromCurrentWeatherApi(response: BaseResponse): WeatherVO {

            val location = response.location
            val current = response.current
            val condition = current?.condition

            return WeatherVO(
                day = DateManager.parse(time = current?.lastUpdated, to = "EEEE").orEmpty(),
                date = DateManager.parse(time = current?.lastUpdated).orEmpty(),
                time = DateManager.parse(time = current?.lastUpdated, to = "hh:mm a").orEmpty(),
                tempC = current?.tempC.orInvalid(),
                tempF = current?.tempF.orInvalid(),
                status = condition?.text.orEmpty(),
                statusIcon = condition?.icon.orEmpty(),
                windySpeedKph = current?.windKph.orInvalid(),
                windySpeedMph = current?.windMph.orInvalid(),
                uv = current?.uv.orInvalid(),
                cloud = current?.cloud.orInvalid(),
                location = location?.name.orEmpty(),
            )
        }

        fun fromForecastWeatherApi(location: String, forecast: ForecastDayItemApiVO): WeatherVO {

            val lastUpdated = forecast.hour?.lastOrNull()
            val avgForecast = forecast.day
            val condition = forecast.day?.condition

            return WeatherVO(
                day = DateManager.parse(time = forecast.date, to = "EEEE", from = "yyyy-MM-dd")
                    .orEmpty(),
                date = DateManager.parse(
                    time = forecast.date,
                    to = "dd/MM/yyyy",
                    from = "yyyy-MM-dd"
                ).orEmpty(),
                time = if (lastUpdated != null) DateManager.parse(
                    time = lastUpdated.time,
                    to = "hh:mm a"
                ).orEmpty() else "",
                tempC = avgForecast?.avgtempC.orInvalid(),
                tempF = avgForecast?.avgtempF.orInvalid(),
                status = condition?.text.orEmpty(),
                statusIcon = condition?.icon.orEmpty(),
                windySpeedKph = avgForecast?.maxwindKph.orInvalid(),
                windySpeedMph = avgForecast?.maxwindMph.orInvalid(),
                uv = avgForecast?.uv.orInvalid(),
                cloud = lastUpdated?.cloud.orInvalid(),
                location = location,
            )
        }
    }

    fun toEntity(type: Int) = WeatherEntity(
        day = day,
        date = date,
        time = time,
        tempC = tempC,
        tempF = tempF,
        status = status,
        statusIcon = statusIcon,
        windySpeedKph = windySpeedKph,
        windySpeedMph = windySpeedMph,
        uv = uv,
        cloud = cloud,
        location = location,
        type = type
    )
}
