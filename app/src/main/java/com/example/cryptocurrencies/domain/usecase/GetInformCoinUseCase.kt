package com.example.cryptocurrencies.domain.usecase

import com.example.cryptocurrencies.domain.model.InformDomain
import com.example.cryptocurrencies.domain.repository.CoinRepository

class GetInformCoinUseCase(private val coinRepository: CoinRepository) {
    suspend  fun invoke(name: String): Result<InformDomain> {
        return coinRepository.getInformCoin(name)
    }
}