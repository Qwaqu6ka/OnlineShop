package com.example.onlineshop.models

data class LatestResponse(
    val latest: List<RawProductModel>
)

data class SaleResponse(
    val flash_sale: List<RawProductModel>
)
