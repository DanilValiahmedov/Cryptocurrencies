package com.example.cryptocurrencies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptocurrencies.databinding.FragmentInformationCoinBinding


class InformationCoin : Fragment() {

    private  lateinit var  binding: FragmentInformationCoinBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationCoinBinding.inflate(inflater)
        return binding.root
    }

}