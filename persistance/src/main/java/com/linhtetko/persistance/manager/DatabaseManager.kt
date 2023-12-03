package com.linhtetko.persistance.manager

import com.linhtetko.persistance.daos.base.WeatherDao

interface DatabaseManager {

    fun weatherDao(): WeatherDao

}