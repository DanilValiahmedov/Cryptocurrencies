package com.example.cryptocurrencies.presentation.recycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrencies.presentation.Currency
import com.example.cryptocurrencies.presentation.fragment.InformationCoin
import com.example.cryptocurrencies.R
import com.google.android.material.chip.ChipGroup


class AdapterCoin(private val context: Context,
                  private val fragmentManager: FragmentManager
): RecyclerView.Adapter<AdapterCoin.NewsViewHolder>() {

    private val coinsList: MutableList<RecycleCoin> = mutableListOf()

    fun setCoinsList(list: List<RecycleCoin>) {
        coinsList.clear()
        coinsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = coinsList[position]
        Glide.with(holder.itemView.context).load(item.imageCoin).into(holder.imageCoin)
        holder.nameCoin.text = item.nameCoin
        holder.symbolCoin.text = item.symbolCoin.uppercase()

        when(item.currency){
            Currency.USD -> holder.price.text = "$ ${item.price}"
            Currency.RUB -> holder.price.text = "₽ ${item.price}"
        }

        if (item.priceChange == 0.0 ) {
            holder.priceChange.text = "0%"
            holder.priceChange.setTextColor(
                ContextCompat.getColor(context, R.color.black)
            )
        } else if (item.priceChange > 0 ) {
            holder.priceChange.text = "+ ${item.priceChange}%"
            holder.priceChange.setTextColor(
                ContextCompat.getColor(context, R.color.plus)
            )
        }  else {
            holder.priceChange.text = "- ${item.priceChange * (-1)}%"
            holder.priceChange.setTextColor(
                ContextCompat.getColor(context, R.color.minus)
            )
        }

        holder.chip.setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(R.id.mainActiv, InformationCoin.newInstance(item.idCoin))
                .addToBackStack(null)
                .commit()
        }


    }

    override fun getItemCount(): Int {
        return coinsList.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameCoin: TextView = itemView.findViewById(R.id.nameCoin)
        val symbolCoin: TextView = itemView.findViewById(R.id.symbolCoin)
        val price: TextView = itemView.findViewById(R.id.price)
        val priceChange: TextView = itemView.findViewById(R.id.priceСhange)
        val imageCoin: ImageView = itemView.findViewById(R.id.imageCoin)
        val chip: ChipGroup = itemView.findViewById(R.id.chip)
    }
}
