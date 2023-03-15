package com.example.onlineshop.api

import android.graphics.BitmapFactory
import com.example.onlineshop.App
import com.example.onlineshop.models.ProductModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {

    suspend fun getSaleList(): List<ProductModel>? {
        val response = App.api.getSale().execute().body()
        val rawProducts = response?.flash_sale
        val products = rawProducts?.map {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                val responseImg = App.api.getImage(it.image_url).execute().body()
                val image = responseImg?.byteStream().use(BitmapFactory::decodeStream)

                ProductModel(
                    category = it.category,
                    name = it.name,
                    price = it.price,
                    discount = it.discount,
                    image = image
                )
            }
        }
        return products
    }

    suspend fun getLatestList(): List<ProductModel>? {
        val response = App.api.getLatest().execute().body()
        val rawProducts = response?.latest
        val products = rawProducts?.map {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                val responseImg = App.api.getImage(it.image_url).execute().body()
                val image = responseImg?.byteStream().use(BitmapFactory::decodeStream)

                ProductModel(
                    category = it.category,
                    name = it.name,
                    price = it.price,
                    discount = it.discount,
                    image = image
                )
            }
        }
        return products
    }
}