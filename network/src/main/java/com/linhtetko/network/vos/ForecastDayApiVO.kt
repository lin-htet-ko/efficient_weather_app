package com.linhtetko.network.vos

import com.google.gson.annotations.SerializedName


data class ForecastApiVO(@SerializedName("forecastday") val days: List<ForecastDayItemApiVO>? = null)

data class ForecastDayItemApiVO(
    val date: String? = null,
    @SerializedName("date_epoch")
    val dateEpoch: Long? = null,
    val day: ForecastDayApiVO? = null,
    val astro: AstroApiVO? = null,
    @SerializedName("hour")
    val hour: List<WeatherStatusApiVO>? = null
)

data class ForecastDayApiVO(
    @SerializedName("maxtemp_c")
    val maxtempC: Double? = null,
    @SerializedName("maxtemp_f")
    val maxtempF: Double? = null,
    @SerializedName("mintemp_c")
    val mintempC: Double? = null,
    @SerializedName("mintemp_f")
    val mintempF: Double? = null,
    @SerializedName("avgtemp_c")
    val avgtempC: Double? = null,
    @SerializedName("avgtemp_f")
    val avgtempF: Double? = null,
    @SerializedName("maxwind_mph")
    val maxwindMph: Double? = null,
    @SerializedName("maxwind_kph")
    val maxwindKph: Double? = null,
    @SerializedName("totalprecip_mm")
    val totalprecipMm: Double? = null,
    @SerializedName("totalprecip_in")
    val totalprecipIn: Double? = null,
    @SerializedName("totalsnow_cm")
    val totalsnowCm: Double? = null,
    @SerializedName("avgvis_km")
    val avgvisKm: Double? = null,
    @SerializedName("avgvis_miles")
    val avgvisMiles: Double? = null,
    @SerializedName("avghumidity")
    val avghumidity: Double? = null,
    @SerializedName("daily_will_it_rain")
    val dailyWillItRain: Int? = null,
    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Int? = null,
    @SerializedName("daily_will_it_snow")
    val dailyWillItSnow: Int? = null,
    @SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int? = null,
    @SerializedName("uv")
    val uv: Double? = null,
    val condition: WeatherConditionApiVO? = null
)

data class AstroApiVO(
    @SerializedName("sunrise")
    val sunrise: String? = null,
    @SerializedName("sunset")
    val sunset: String? = null,
    @SerializedName("moonrise")
    val moonrise: String? = null,
    @SerializedName("moonset")
    val moonset: String? = null,
    @SerializedName("moon_phase")
    val moonPhase: String? = null,
    @SerializedName("moon_illumination")
    val moonIllumination: Int? = null,
    @SerializedName("is_moon_up")
    val isMoonUp: Int? = null,
    @SerializedName("is_sun_up")
    val isSunUp: Int? = null
)