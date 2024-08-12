package com.example.cryptocurrencies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencies.databinding.ActivityMainBinding
import com.example.cryptocurrencies.fragment.CoinsList
import com.example.cryptocurrencies.fragment.InformationCoin


class MainActivity : AppCompatActivity() {

    private  lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coinViewModel = ViewModelProvider(this).get(CoinViewModel::class.java)


        supportFragmentManager.beginTransaction().replace(R.id.mainActiv, CoinsList()).commit()
        if (coinViewModel.idCoin.value != null) {
            supportFragmentManager.beginTransaction().replace(R.id.mainActiv,
                InformationCoin.newInstance(coinViewModel.idCoin.value!!)).commit()
        }

    }
}