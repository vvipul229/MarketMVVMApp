package com.vipul.marketmvvmapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.vipul.marketmvvmapp.model.MarketModel
import com.vipul.marketmvvmapp.repository.MarketRepository
import com.vipul.marketmvvmapp.utils.Utils
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarketViewModel(private val marketRepository: MarketRepository) : ViewModel() {

    val movieList = MutableLiveData<ArrayList<MarketModel>>()
    val errorMessage = MutableLiveData<String>()
    val currentState = MutableLiveData<String>()

    fun getMarketList() {
        currentState.postValue(Utils.LOADING)
        marketRepository.getMarketList().enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                currentState.postValue(Utils.LOADING_COMPLETED)
                if (response.body() != null) {
                    val responseObj =
                        JSONObject(response.body().toString()).getJSONObject("response")
                    val list = ArrayList<MarketModel>()
                    responseObj.keys().forEach {
                        if (responseObj.has(it)) {
                            val marketObject = responseObj.getJSONObject(it)
                            list.add(
                                MarketModel(
                                    it,
                                    marketObject.optString("name"),
                                    marketObject.optString("latitude").toDouble(),
                                    marketObject.optString("longitude").toDouble()
                                )
                            )
                        }
                    }
                    movieList.postValue(list)

                } else {
                    errorMessage.postValue("Some Error Occurred")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                currentState.postValue(Utils.LOADING_COMPLETED)
                errorMessage.postValue(t.localizedMessage)
            }

        })
    }

}