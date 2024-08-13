package com.example.cryptocurrencies.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cryptocurrencies.databinding.FragmentInformationCoinBinding
import com.example.cryptocurrencies.presentation.CoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationCoin : Fragment() {

    private  lateinit var  binding: FragmentInformationCoinBinding
    private lateinit var coinViewModel: CoinViewModel
    private lateinit var idCoin: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationCoinBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            idCoin = it.getString("id") ?: "bitcoin"
        }

        coinViewModel = ViewModelProvider(requireActivity()).get(CoinViewModel::class.java)

        coinViewModel.getInformCoinById(idCoin)

        coinViewModel.informCoin.observe(viewLifecycleOwner) {
            binding.nameCoin.text = it[0]
            Glide.with(requireContext()).load(it[1]).into(binding.imageCoin)
            binding.description.text = it[2]
            binding.categories.text = it[3]
        }

        coinViewModel.loading.observe(viewLifecycleOwner) {
            if(it) binding.progressBar.visibility = View.VISIBLE
            else {
                binding.progressBar.visibility = View.GONE
                binding.cardInform.visibility = View.VISIBLE
                binding.nameCoin.text = ""
            }
        }

        coinViewModel.error.observe(viewLifecycleOwner) {
            if(it) {
                binding.cardInform.visibility = View.GONE
                binding.errorLoading.visibility = View.VISIBLE
            } else {
                binding.errorLoading.visibility = View.GONE
                binding.nameCoin.text = ""
            }
        }

        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.restart.setOnClickListener {
            coinViewModel.getInformCoinById(idCoin)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String) =  InformationCoin().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

}