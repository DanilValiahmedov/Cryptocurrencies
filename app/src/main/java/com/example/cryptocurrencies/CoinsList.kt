package com.example.cryptocurrencies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptocurrencies.databinding.FragmentCoinsListBinding



class CoinsList : Fragment() {

    private  lateinit var  binding: FragmentCoinsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoinsListBinding.inflate(inflater)
        return binding.root
    }


}