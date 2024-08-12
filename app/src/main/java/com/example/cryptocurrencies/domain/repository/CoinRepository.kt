package com.example.cryptocurrencies.domain.repository

import com.example.cryptocurrencies.domain.model.CoinsDomain
import com.example.cryptocurrencies.domain.model.InformDomain

interface CoinRepository {

    suspend fun getListCoins(section: String): Result<List<CoinsDomain>>

    suspend fun getInformCoin(section: String): Result<InformDomain>

}