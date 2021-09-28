package com.gustavohisan.apelieuser.store.model

internal data class ProductCategory(
    val category: String,
    val productList: List<Product>
)
