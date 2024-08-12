package com.example.cryptocurrencies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencies.presentation.recycleview.RecycleCoin
import com.example.cryptocurrencies.domain.usecase.GetInformCoinUseCase
import com.example.cryptocurrencies.domain.usecase.GetListCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getInformCoinUseCase: GetInformCoinUseCase,
    private val getListCoinsUseCase: GetListCoinsUseCase
): ViewModel() {
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


    private fun setUpListCoins(currency: String, coinsCurrency: Currency) {
        _idCoin.value = null
        _error.value = false
        viewModelScope.launch {
            _loading.value = true
            val result = getListCoinsUseCase.invoke(currency)
            if (result.isSuccess) {
                val list = result.getOrNull()?.map {
                    RecycleCoin(
                        nameCoin = it.name,
                        symbolCoin = it.symbol,
                        price = it.current_price,
                        priceChange = it.price_change_percentage_24h,
                        imageCoin = it.image,
                        idCoin = it.id,
                        currency = coinsCurrency
                    )
                }
                if (list == null) {
                    _loading.value = false
                    _error.value = true
                } else {
                    _liveDataCoins.value = list!!
                    _loading.value = false
                }
            } else {
                _loading.value = false
                _error.value = true
            }
        }
    }


    fun getInformCoinById(id: String) {
        _idCoin.value = id
        _error.value = false
        viewModelScope.launch {
            _loading.value = true
            val result = getInformCoinUseCase.invoke(id)
            if (result.isSuccess) {
                val listInform = mutableListOf<String>()
                result.map {
                    listInform.add(it.name)
                    listInform.add(it.image)
                    listInform.add(it.description)
                    listInform.add(it.categories)
                }
                if (listInform == null) {
                    _loading.value = false
                    _error.value = true
                } else {
                    _loading.value = false
                    _informCoin.value = listInform
                }
            } else {
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