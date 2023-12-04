package com.linhtetko.persistance.manager

import com.linhtetko.persistance.daos.base.WeatherDao
import com.linhtetko.persistance.databases.base.EfficientDatabase

internal class DatabaseManagerImpl(
    private val roomEfficientDatabase: EfficientDatabase
) : DatabaseManager {

    override fun weatherDao(): WeatherDao {
        return roomEfficientDatabase.weatherDao()
    }
}