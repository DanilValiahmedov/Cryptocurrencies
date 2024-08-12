package com.example.cryptocurrencies.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencies.R
import com.example.cryptocurrencies.databinding.ActivityMainBinding
import com.example.cryptocurrencies.presentation.fragment.CoinsList
import com.example.cryptocurrencies.presentation.fragment.InformationCoin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private  lateinit var  binding: ActivityMainBinding
    private lateinit var coinViewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coinViewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        if (coinViewModel.idCoin.value != null) {

            val backStackCount = supportFragmentManager.backStackEntryCount
            for (i in 0 until backStackCount) {
                supportFragmentManager.popBackStackImmediate()
            }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainActiv, CoinsList())
                .commit()

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainActiv, InformationCoin.newInstance(coinViewModel.idCoin.value!!))
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainActiv, CoinsList())
                .commit()
        }

    }
}