package com.example.cryptocurrencies.data.repository

import com.example.cryptocurrencies.data.datasource.CoinsDataSource
import com.example.cryptocurrencies.data.datasource.IformDataSource
import com.example.cryptocurrencies.domain.model.CoinsDomain
import com.example.cryptocurrencies.domain.model.InformDomain
import com.example.cryptocurrencies.domain.repository.CoinRepository
import java.text.DecimalFormat

class CoinRepositoryImpl: CoinRepository {
    override suspend fun getListCoins(currency: String): Result<List<CoinsDomain>> {
        val result = CoinsDataSource.getCoinsList(currency)
        return result.map { coinsDataClass ->
            coinsDataClass.map {
                CoinsDomain(
                    id = it.id,
                    symbol = it.symbol,
                    name = it.name,
                    image = it.image,
                    current_price = if (it.current_price >= 0.01) DecimalFormat("#,###.##").format(it.current_price)
                    else it.current_price.toString(),
                    price_change_percentage_24h = (it.price_change_percentage_24h * 100).toInt() / 100.0,
                )
            }
        }
    }


    override suspend fun getInformCoin(name: String): Result<InformDomain> {
        val result = IformDataSource.getInformCoin(name)
        return result.map { it ->
            var categories = ""
            it.categories.forEach {
                categories = categories + it + ". "
            }
            InformDomain(
                name = it.name,
                categories = categories,
                description = if (it.description.en != "") it.description.en
                else "There is no description of this coin in our database.",
                image = it.image.large,
            )

        }
    }

}
