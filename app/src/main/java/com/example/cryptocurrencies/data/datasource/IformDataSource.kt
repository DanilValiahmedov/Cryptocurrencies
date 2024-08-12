package com.example.cryptocurrencies.data.datasource

import com.example.cryptocurrencies.data.Network
import com.example.cryptocurrencies.data.model.InformCoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

object IformDataSource {
    suspend fun getInformCoin(name: String): Result<InformCoin> = request {
        Network.informCoinApi.getInformByName(name)
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