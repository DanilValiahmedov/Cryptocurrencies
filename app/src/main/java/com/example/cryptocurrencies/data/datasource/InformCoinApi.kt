package com.example.cryptocurrencies.data.datasource

import com.example.cryptocurrencies.data.model.InformCoin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InformCoinApi {
    @GET("coins/{name}")
    suspend fun getInformByName(@Path("name") name: String): Response<InformCoin>
}