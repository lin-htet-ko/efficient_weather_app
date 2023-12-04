package com.linhtetko.network.vos


import com.google.gson.annotations.SerializedName

data class WeatherConditionApiVO(
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("icon")
    val icon: String? = null,
    @SerializedName("code")
    val code: Int? = null
)

data class WeatherStatusApiVO(
    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Long? = null,
    @SerializedName("last_updated")
    val lastUpdated: String? = null,
    val time: String? = null,
    @SerializedName("temp_c")
    val tempC: Double? = null,
    @SerializedName("temp_f")
    val tempF: Double? = null,
    @SerializedName("is_day")
    val isDay: Int? = null,
    @SerializedName("condition")
    val condition: WeatherConditionApiVO? = null,
    @SerializedName("wind_mph")
    val windMph: Double? = null,
    @SerializedName("wind_kph")
    val windKph: Double? = null,
    @SerializedName("wind_degree")
    val windDegree: Int? = null,
    @SerializedName("wind_dir")
    val windDir: String? = null,
    @SerializedName("pressure_mb")
    val pressureMb: Double? = null,
    @SerializedName("pressure_in")
    val pressureIn: Double? = null,
    @SerializedName("precip_mm")
    val precipMm: Double? = null,
    @SerializedName("precip_in")
    val precipIn: Double? = null,
    @SerializedName("humidity")
    val humidity: Int? = null,
    @SerializedName("cloud")
    val cloud: Int? = null,
    @SerializedName("feelslike_c")
    val feelslikeC: Double? = null,
    @SerializedName("feelslike_f")
    val feelslikeF: Double? = null,
    @SerializedName("vis_km")
    val visKm: Double? = null,
    @SerializedName("vis_miles")
    val visMiles: Double? = null,
    @SerializedName("uv")
    val uv: Double? = null,
    @SerializedName("gust_mph")
    val gustMph: Double? = null,
    @SerializedName("gust_kph")
    val gustKph: Double? = null
)