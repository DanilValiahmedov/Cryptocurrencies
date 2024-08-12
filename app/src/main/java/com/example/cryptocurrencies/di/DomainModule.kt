package com.example.cryptocurrencies.di

import com.example.cryptocurrencies.domain.repository.CoinRepository
import com.example.cryptocurrencies.domain.usecase.GetInformCoinUseCase
import com.example.cryptocurrencies.domain.usecase.GetListCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetInformCoinUseCase(coinRepository: CoinRepository): GetInformCoinUseCase {
        return GetInformCoinUseCase(coinRepository)
    }

    @Provides
    fun provideGetListCoinsUseCase(coinRepository: CoinRepository): GetListCoinsUseCase {
        return GetListCoinsUseCase(coinRepository)
    }

}