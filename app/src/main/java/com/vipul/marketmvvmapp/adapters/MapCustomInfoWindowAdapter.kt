package com.vipul.marketmvvmapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.vipul.marketmvvmapp.R
import com.vipul.marketmvvmapp.model.MarketModel


class MapCustomInfoWindowAdapter(context: Context) : GoogleMap.InfoWindowAdapter {
    var view: View =
        LayoutInflater.from(context).inflate(R.layout.laoyut_map_custom_info_window, null)

    override fun getInfoContents(p0: Marker): View {
        val data=p0.tag as MarketModel
        view.findViewById<TextView>(R.id.tvTitle).text = data.name
        view.findViewById<TextView>(R.id.tvDate).text = data.date

        return view
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }
}