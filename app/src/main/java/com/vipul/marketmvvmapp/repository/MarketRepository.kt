package com.vipul.marketmvvmapp.repository

import com.vipul.marketmvvmapp.retrofit.RetrofitService

class MarketRepository(private val retrofitService: RetrofitService) {
    fun getMarketList() = retrofitService.getMarketList()

}