package com.linhtetko.network.vos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastApiVO(@SerialName("forecastday") val days: List<ForecastDayItemApiVO>? = null)

@Serializable
data class ForecastDayItemApiVO(
    val date: String? = null,
    @SerialName("date_epoch")
    val dateEpoch: Long? = null,
    val day: ForecastDayApiVO? = null
)

@Serializable
data class ForecastDayApiVO(
    @SerialName("maxtemp_c")
    val maxtempC: Double? = null,
    @SerialName("maxtemp_f")
    val maxtempF: Double? = null,
    @SerialName("mintemp_c")
    val mintempC: Double? = null,
    @SerialName("mintemp_f")
    val mintempF: Double? = null,
    @SerialName("avgtemp_c")
    val avgtempC: Double? = null,
    @SerialName("avgtemp_f")
    val avgtempF: Double? = null,
    @SerialName("maxwind_mph")
    val maxwindMph: Double? = null,
    @SerialName("maxwind_kph")
    val maxwindKph: Double? = null,
    @SerialName("totalprecip_mm")
    val totalprecipMm: Double? = null,
    @SerialName("totalprecip_in")
    val totalprecipIn: Double? = null,
    @SerialName("totalsnow_cm")
    val totalsnowCm: Double? = null,
    @SerialName("avgvis_km")
    val avgvisKm: Double? = null,
    @SerialName("avgvis_miles")
    val avgvisMiles: Double? = null,
    @SerialName("avghumidity")
    val avghumidity: Double? = null,
    @SerialName("daily_will_it_rain")
    val dailyWillItRain: Int? = null,
    @SerialName("daily_chance_of_rain")
    val dailyChanceOfRain: Int? = null,
    @SerialName("daily_will_it_snow")
    val dailyWillItSnow: Int? = null,
    @SerialName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int? = null,
    @SerialName("uv")
    val uv: Double? = null
)