package com.example.cryptocurrencies.recycleview

import com.example.cryptocurrencies.Currency

data class RecycleCoin(
    val nameCoin: String,
    val symbolCoin: String,
    val price: String,
    val priceChange: Double,
    val imageCoin: String,
    val idCoin: String,
    val currency: Currency,
)


