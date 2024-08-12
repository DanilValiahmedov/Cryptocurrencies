package com.example.cryptocurrencies.data.datasource

import com.example.cryptocurrencies.data.model.Coins
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinsApi {
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): Response<List<Coins>>
}