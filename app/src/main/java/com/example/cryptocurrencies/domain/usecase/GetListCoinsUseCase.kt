package com.example.cryptocurrencies.domain.usecase

import com.example.cryptocurrencies.domain.model.CoinsDomain
import com.example.cryptocurrencies.domain.repository.CoinRepository

class GetListCoinsUseCase(private val coinRepository: CoinRepository) {
    suspend  fun invoke(currency: String): Result<List<CoinsDomain>> {
        return coinRepository.getListCoins(currency)
    }
}