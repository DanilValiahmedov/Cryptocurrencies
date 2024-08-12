package com.example.cryptocurrencies.data.model

import kotlinx.serialization.Serializable

@Serializable
data class InformCoin(
    val name: String,
    val categories: List<String>,
    val description: DescriptionCoin,
    val image: ImageCoin,
)

@Serializable
data class  ImageCoin(
    val large: String,
)

@Serializable
data class DescriptionCoin(
    val en: String,
)
