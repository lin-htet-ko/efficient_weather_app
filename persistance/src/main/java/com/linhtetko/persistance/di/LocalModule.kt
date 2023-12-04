package com.linhtetko.persistance.di

import android.content.Context
import com.linhtetko.persistance.daos.base.WeatherDao
import com.linhtetko.persistance.databases.base.EfficientDatabase
import com.linhtetko.persistance.databases.impl.RoomEfficientDatabase
import com.linhtetko.persistance.manager.DatabaseManager
import com.linhtetko.persistance.manager.DatabaseManagerImpl

object LocalModule {

    fun getManager(context: Context): DatabaseManager = DatabaseManagerImpl(EfficientDatabase.initialize(context))

}