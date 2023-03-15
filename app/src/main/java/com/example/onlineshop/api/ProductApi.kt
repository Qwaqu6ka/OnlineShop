package com.example.onlineshop.api

import com.example.onlineshop.models.LatestResponse
import com.example.onlineshop.models.SaleResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductApi {
    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    fun getLatest(): Call<LatestResponse>

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    fun getSale(): Call<SaleResponse>

    @GET
    fun getImage(@Url url: String): Call<ResponseBody>
}