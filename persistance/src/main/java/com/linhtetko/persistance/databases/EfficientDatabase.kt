package com.linhtetko.persistance.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.linhtetko.persistance.daos.impl.RoomWeatherDao
import com.linhtetko.persistance.entities.WeatherEntity

@Database(entities = [WeatherEntity::class], version = Schema.DB_VERSION)
abstract class EfficientDatabase : RoomDatabase() {

    companion object {

        lateinit var INSTANCE: EfficientDatabase

        fun initialize(context: Context): EfficientDatabase {
            if (!::INSTANCE.isInitialized)
                INSTANCE =
                    Room.databaseBuilder(context, EfficientDatabase::class.java, Schema.DB_NAME)
                        .build()
            return INSTANCE
        }
    }

    abstract fun weatherDao(): RoomWeatherDao

}