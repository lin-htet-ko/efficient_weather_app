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
    val temp: String,
    val status: String,
    val statusIcon: String,
    val windySpeed: String,
    val uv: String,
    val rain: String,
    val location: String,

    val type: Int
) {

    companion object {

        const val TYPE_CURRENT = 0
        const val TYPE_FORECAST = 1

    }
}
