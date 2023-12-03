package com.linhtetko.efficientweatherapp.di

import com.linhtetko.network.api.base.ApiManager
import com.linhtetko.network.di.ApiModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiManager(): ApiManager = ApiModule.apiManager
}