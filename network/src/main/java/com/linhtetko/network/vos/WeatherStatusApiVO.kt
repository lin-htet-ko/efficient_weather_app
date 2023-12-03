package com.linhtetko.network.vos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherConditionApiVO(
    @SerialName("text")
    val text: String? = null,
    @SerialName("icon")
    val icon: String? = null,
    @SerialName("code")
    val code: Int? = null
)

@Serializable
data class WeatherStatusApiVO(
    @SerialName("last_updated_epoch")
    val lastUpdatedEpoch: Long? = null,
    @SerialName("last_updated")
    val lastUpdated: String? = null,
    @SerialName("temp_c")
    val tempC: Double? = null,
    @SerialName("temp_f")
    val tempF: Double? = null,
    @SerialName("is_day")
    val isDay: Int? = null,
    @SerialName("condition")
    val condition: WeatherConditionApiVO? = null,
    @SerialName("wind_mph")
    val windMph: Double? = null,
    @SerialName("wind_kph")
    val windKph: Double? = null,
    @SerialName("wind_degree")
    val windDegree: Int? = null,
    @SerialName("wind_dir")
    val windDir: String? = null,
    @SerialName("pressure_mb")
    val pressureMb: Double? = null,
    @SerialName("pressure_in")
    val pressureIn: Double? = null,
    @SerialName("precip_mm")
    val precipMm: Double? = null,
    @SerialName("precip_in")
    val precipIn: Double? = null,
    @SerialName("humidity")
    val humidity: Int? = null,
    @SerialName("cloud")
    val cloud: Int? = null,
    @SerialName("feelslike_c")
    val feelslikeC: Double? = null,
    @SerialName("feelslike_f")
    val feelslikeF: Double? = null,
    @SerialName("vis_km")
    val visKm: Double? = null,
    @SerialName("vis_miles")
    val visMiles: Double? = null,
    @SerialName("uv")
    val uv: Double? = null,
    @SerialName("gust_mph")
    val gustMph: Double? = null,
    @SerialName("gust_kph")
    val gustKph: Double? = null
)