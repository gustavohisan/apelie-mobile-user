package com.gustavohisan.apelieuser.orders.model

internal data class ProductCategory(
    val category: String,
    val productList: List<Product>
)
