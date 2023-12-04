package com.linhtetko.persistance.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.linhtetko.persistance.databases.Schema

@Entity(tableName = Schema.Table.TB_WEATHER, indices = [Index("type")])
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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
    val location: String,

    val type: Int
) {

    companion object {

        const val TYPE_CURRENT = 0
        const val TYPE_FORECAST = 1

    }
}
