package com.example.onlineshop.models

import android.graphics.Bitmap

data class RawProductModel(
    val category: String,
    val name: String,
    val price: Double,
    val discount: Int?,
    val image_url: String
)

data class ProductModel(
    val category: String,
    val name: String,
    val price: Double,
    val discount: Int?,
    val image: Bitmap
)
