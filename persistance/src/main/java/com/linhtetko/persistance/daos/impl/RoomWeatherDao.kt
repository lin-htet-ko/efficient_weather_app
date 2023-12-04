package com.linhtetko.persistance.daos.impl

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linhtetko.persistance.daos.base.WeatherDao
import com.linhtetko.persistance.entities.WeatherEntity
import com.linhtetko.persistance.databases.Schema
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RoomWeatherDao: WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(weather: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(vararg weathers: WeatherEntity)

    @Query("SELECT * FROM ${Schema.Table.TB_WEATHER} WHERE type=${WeatherEntity.TYPE_CURRENT} ORDER BY id DESC LIMIT 1")
    override fun getLastWeather(): Flow<WeatherEntity>

    @Query("SELECT * FROM ${Schema.Table.TB_WEATHER} WHERE type=:type ORDER BY id DESC LIMIT :count")
    override fun getWeathersByType(type: Int, count: Int): Flow<List<WeatherEntity>>
}