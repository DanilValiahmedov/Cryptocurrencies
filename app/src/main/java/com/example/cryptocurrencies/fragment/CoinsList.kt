package com.example.cryptocurrencies.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencies.CoinViewModel
import com.example.cryptocurrencies.Currency
import com.example.cryptocurrencies.R
import com.example.cryptocurrencies.databinding.FragmentCoinsListBinding
import com.example.cryptocurrencies.recycleview.AdapterCoin


class CoinsList() : Fragment() {

    private  lateinit var  binding: FragmentCoinsListBinding
    private lateinit var coinViewModel: CoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoinsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recycleView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = AdapterCoin(requireContext(), parentFragmentManager)
        recyclerView.adapter = adapter

        coinViewModel = ViewModelProvider(requireActivity()).get(CoinViewModel::class.java)

        coinViewModel.currencySelection(coinViewModel.selectedCurrency.value ?: Currency.USD)

        coinViewModel.liveDataCoins.observe(viewLifecycleOwner) {
            adapter.setCoinsList(it)
        }

        coinViewModel.selectedCurrency.observe(viewLifecycleOwner) {
            activeButton(it)
        }

        coinViewModel.loading.observe(viewLifecycleOwner) {
            if(it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        coinViewModel.error.observe(viewLifecycleOwner) {
            if(it) {
                binding.errorLoading.visibility = View.VISIBLE
            } else {
                binding.errorLoading.visibility = View.GONE
            }
        }

        binding.usdBut.setOnClickListener {
            adapter.setCoinsList(listOf())
            coinViewModel.currencySelection(Currency.USD)
        }

        binding.rubBut.setOnClickListener {
            adapter.setCoinsList(listOf())
            coinViewModel.currencySelection(Currency.RUB)
        }

        binding.restart.setOnClickListener {
            when(coinViewModel.selectedCurrency.value) {
                Currency.USD -> coinViewModel.currencySelection(Currency.USD)
                Currency.RUB -> coinViewModel.currencySelection(Currency.RUB)
                else -> coinViewModel.currencySelection(Currency.USD)
            }
        }

    }

    private fun activeButton(currency: Currency) {
        when(currency) {
            Currency.USD -> activeButtonChangingColor(binding.usdBut, binding.rubBut)
            Currency.RUB -> activeButtonChangingColor(binding.rubBut, binding.usdBut)
        }
    }

    private fun activeButtonChangingColor(butActiv: Button, butNoActiv: Button) {
        butActiv.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainColor))
        butActiv.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.activeButton))
        butNoActiv.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        butNoActiv.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.inactiveButton))
    }


}