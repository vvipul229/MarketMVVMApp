package com.vipul.marketmvvmapp.retrofit

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("get.php")
    fun getMarketList(): Call<JsonObject>

}