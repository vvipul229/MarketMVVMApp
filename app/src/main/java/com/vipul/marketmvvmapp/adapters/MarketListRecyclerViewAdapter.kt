package com.vipul.marketmvvmapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vipul.marketmvvmapp.R
import com.vipul.marketmvvmapp.model.MarketModel

class MarketListRecyclerViewAdapter :
    RecyclerView.Adapter<MarketListRecyclerViewAdapter.MyMovieViewHolder>() {

    var marketAl = ArrayList<MarketModel>()

    inner class MyMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val date: TextView = itemView.findViewById(R.id.tvDate)
    }

    fun setMarketList(list: ArrayList<MarketModel>) {
        marketAl.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarketListRecyclerViewAdapter.MyMovieViewHolder {
        return MyMovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_market_recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: MarketListRecyclerViewAdapter.MyMovieViewHolder,
        position: Int
    ) {
        val marketData = marketAl[position]
        holder.title.text = marketData.name
        holder.date.text = marketData.date
    }

    override fun getItemCount(): Int {
        return marketAl.size
    }

}