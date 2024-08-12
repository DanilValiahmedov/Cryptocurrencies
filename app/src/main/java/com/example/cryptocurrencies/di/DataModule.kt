package com.example.cryptocurrencies.di

import com.example.cryptocurrencies.data.repository.CoinRepositoryImpl
import com.example.cryptocurrencies.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCoinRepository(): CoinRepository {
        return CoinRepositoryImpl()
    }

}