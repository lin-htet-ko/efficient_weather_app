package com.linhtetko.persistance.databases.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.linhtetko.persistance.daos.impl.RoomWeatherDao
import com.linhtetko.persistance.databases.Schema
import com.linhtetko.persistance.databases.base.EfficientDatabase
import com.linhtetko.persistance.entities.WeatherEntity

@Database(entities = [WeatherEntity::class], version = Schema.DB_VERSION)
internal abstract class RoomEfficientDatabase : RoomDatabase(), EfficientDatabase {

    abstract override fun weatherDao(): RoomWeatherDao

}