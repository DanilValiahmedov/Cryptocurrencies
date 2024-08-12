package com.example.cryptocurrencies.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinsApi {
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): List<Coins>
}