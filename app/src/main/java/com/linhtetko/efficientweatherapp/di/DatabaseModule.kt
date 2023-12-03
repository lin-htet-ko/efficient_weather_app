package com.linhtetko.efficientweatherapp.di

import android.content.Context
import com.linhtetko.persistance.daos.base.WeatherDao
import com.linhtetko.persistance.databases.EfficientDatabase
import com.linhtetko.persistance.manager.DatabaseManager
import com.linhtetko.persistance.manager.DatabaseManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabaseManager(@ApplicationContext context: Context): DatabaseManager =
        DatabaseManagerImpl(efficientDatabase = EfficientDatabase.initialize(context))

    @Singleton
    @Provides
    fun provideWeatherDao(databaseManager: DatabaseManager): WeatherDao = databaseManager.weatherDao()
}