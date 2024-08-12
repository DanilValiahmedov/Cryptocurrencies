package com.example.cryptocurrencies.data.datasource

import com.example.cryptocurrencies.data.Network
import com.example.cryptocurrencies.data.model.Coins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

object CoinsDataSource {
    suspend fun getCoinsList(currency: String): Result<List<Coins>> = request {
        Network.coinsApi.getCoins(currency)
    }

    private suspend fun <T>request(requestApi: suspend () -> Response<T>): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = requestApi()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success(body)
                    } else {
                        Result.failure(Exception())
                    }
                } else {
                    Result.failure(Exception())
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    }
}