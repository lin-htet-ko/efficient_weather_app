package com.linhtetko.persistance.databases.base

import android.content.Context
import androidx.room.Room
import com.linhtetko.persistance.daos.base.WeatherDao
import com.linhtetko.persistance.databases.Schema
import com.linhtetko.persistance.databases.impl.RoomEfficientDatabase

interface EfficientDatabase {

    companion object {

        private lateinit var INSTANCE: EfficientDatabase

        fun initialize(context: Context): EfficientDatabase {
            if (!::INSTANCE.isInitialized)
                INSTANCE =
                    Room.databaseBuilder(context, RoomEfficientDatabase::class.java, Schema.DB_NAME)
                        .build()
            return INSTANCE
        }
    }

    fun weatherDao(): WeatherDao


}