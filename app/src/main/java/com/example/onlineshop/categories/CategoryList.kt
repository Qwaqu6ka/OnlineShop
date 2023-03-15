package com.example.onlineshop.categories

import com.example.onlineshop.R
import com.example.onlineshop.models.CategoryModel

class CategoryList {
    private val list = listOf(
        CategoryModel(
            R.drawable.ic_phone,
            "Phones"
        ),
        CategoryModel(
            R.drawable.ic_headphone,
            "Headphones"
        ),
        CategoryModel(
            R.drawable.ic_gamepad,
            "Games"
        ),
        CategoryModel(
            R.drawable.ic_car,
            "Cars"
        ),
        CategoryModel(
            R.drawable.ic_bed,
            "Furniture"
        ),
        CategoryModel(
            R.drawable.ic_robot,
            "Kids"
        )
    )

    fun getList() = list
}