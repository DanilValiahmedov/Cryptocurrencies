package com.example.cryptocurrencies.domain.model

data class CoinsDomain (
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: String,
    val price_change_percentage_24h: Double,
)