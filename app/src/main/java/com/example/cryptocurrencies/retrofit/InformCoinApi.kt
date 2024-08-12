package com.example.cryptocurrencies.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface InformCoinApi {
    @GET("coins/{name}")
    suspend fun getInformByName(@Path("name") name: String): InformCoin
}