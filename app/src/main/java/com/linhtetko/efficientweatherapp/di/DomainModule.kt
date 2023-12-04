package com.linhtetko.efficientweatherapp.di

import android.content.Context
import com.linhtetko.domain.di.DataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun provideDataModule(@ApplicationContext context: Context): DataModule =
        DataModule(context)

}