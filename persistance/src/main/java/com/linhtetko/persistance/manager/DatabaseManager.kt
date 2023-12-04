package com.linhtetko.persistance.manager

import android.content.Context
import com.linhtetko.persistance.daos.base.WeatherDao
import com.linhtetko.persistance.databases.base.EfficientDatabase

interface DatabaseManager {

    fun weatherDao(): WeatherDao

}