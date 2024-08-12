package com.example.cryptocurrencies.data

import com.example.cryptocurrencies.data.datasource.CoinsApi
import com.example.cryptocurrencies.data.datasource.InformCoinApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/v3/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val coinsApi = retrofit.create(CoinsApi::class.java)

    val informCoinApi = retrofit.create(InformCoinApi::class.java)

}