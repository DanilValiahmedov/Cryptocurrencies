package com.example.cryptocurrencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencies.recycleview.RecycleCoin
import com.example.cryptocurrencies.retrofit.CoinsApi
import com.example.cryptocurrencies.retrofit.InformCoinApi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat

class CoinViewModel: ViewModel() {
    private val _liveDataCoins = MutableLiveData<List<RecycleCoin>>()
    private val _selectedCurrency = MutableLiveData<Currency>()
    private val _informCoin = MutableLiveData<List<String>>()
    private val _idCoin = MutableLiveData<String?>()
    private val _loading = MutableLiveData(false)
    private val _error = MutableLiveData(false)

    val liveDataCoins: LiveData<List<RecycleCoin>> = _liveDataCoins
    val selectedCurrency: LiveData<Currency> = _selectedCurrency
    val informCoin: LiveData<List<String>> = _informCoin
    val idCoin: LiveData<String?> = _idCoin
    val loading: LiveData<Boolean> = _loading
    val error: LiveData<Boolean> = _error

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val coinsApi = retrofit.create(CoinsApi::class.java)

    private fun setUpListCoins(currency: String, coinsCurrency: Currency) {
        _idCoin.value = null
        _loading.value = true
        _error.value = false
        viewModelScope.launch {
            try {
                val coins = coinsApi.getCoins(currency)
                val listCoins = coins.map {
                    RecycleCoin(
                        nameCoin = it.name,
                        symbolCoin = it.symbol,
                        price = if (it.current_price >= 0.01) DecimalFormat("#,###.##").format(it.current_price)
                        else it.current_price.toString(),
                        priceChange = (it.price_change_percentage_24h * 100).toInt() / 100.0,
                        imageCoin = it.image,
                        idCoin = it.id,
                        currency = coinsCurrency
                    )
                }
                _loading.value = false
                _liveDataCoins.value = listCoins
            } catch (e: Exception) {
                _loading.value = false
                _error.value = true
            }
        }
    }

    val informCoinApi = retrofit.create(InformCoinApi::class.java)

    fun getInformCoinById(id: String) {
        _idCoin.value = id
        _loading.value = true
        _error.value = false
        viewModelScope.launch {
            try {
                _loading.value = true
                val coins = informCoinApi.getInformByName(id)
                val listInform = mutableListOf<String>()
                listInform.add(coins.name)
                listInform.add(coins.image.large)
                listInform.add(
                    coins.description.en
                )
                var categories = ""
                coins.categories.forEach {
                    categories = categories + it + ". "
                }
                listInform.add(categories)
                _loading.value = false
                _informCoin.value = listInform
            } catch (e: Exception) {
                _loading.value = false
                _error.value = true
            }
        }
    }

    fun currencySelection(coinsCurrency: Currency) {
        when(coinsCurrency) {
            Currency.USD -> {
                _selectedCurrency.value = Currency.USD
                setUpListCoins("usd", Currency.USD)
            }
            Currency.RUB -> {
                _selectedCurrency.value = Currency.RUB
                setUpListCoins("rub", Currency.RUB)
            }
        }
    }
}