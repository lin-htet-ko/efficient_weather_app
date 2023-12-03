package com.linhtetko.persistance.manager

import com.linhtetko.persistance.daos.base.WeatherDao
import com.linhtetko.persistance.databases.EfficientDatabase

class DatabaseManagerImpl(
    private val efficientDatabase: EfficientDatabase
): DatabaseManager {

    override fun weatherDao(): WeatherDao {
        return efficientDatabase.weatherDao()
    }
}