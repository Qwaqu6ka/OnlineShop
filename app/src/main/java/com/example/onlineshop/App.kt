package com.example.onlineshop

import android.app.Application
import com.example.onlineshop.api.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api_ = retrofit.create(ProductApi::class.java)
    }

    companion object {
        private lateinit var api_: ProductApi
        val api: ProductApi
            get() = api_
    }
}