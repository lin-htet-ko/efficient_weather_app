package com.linhtetko.efficientweatherapp.di

import com.linhtetko.domain.di.DataModule
import com.linhtetko.domain.repositories.WeatherRepoImpl
import com.linhtetko.domain.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun bindWeatherRepo(dataModule: DataModule): WeatherRepository {
        return WeatherRepoImpl(dataModule)
    }
}