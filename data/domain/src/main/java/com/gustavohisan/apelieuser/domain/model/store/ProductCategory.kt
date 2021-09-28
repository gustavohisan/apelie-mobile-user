package com.gustavohisan.apelieuser.domain.model.store

data class ProductCategory(
    val category: String,
    val productList: List<Product>
)
