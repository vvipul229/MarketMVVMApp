package com.vipul.marketmvvmapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vipul.marketmvvmapp.repository.MarketRepository

class MarketViewModelFactory(private val marketRepository: MarketRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MarketViewModel::class.java)) {
            MarketViewModel(marketRepository) as T
        } else {
            throw  IllegalArgumentException("No ViewModel Found")
        }
    }

}