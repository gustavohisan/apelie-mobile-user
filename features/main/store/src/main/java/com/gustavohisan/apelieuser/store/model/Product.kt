package com.gustavohisan.apelieuser.store.model

internal data class Product(
    val id: Int,
    val price: Float,
    val name: String,
    val description: String,
    val quantity: Int,
    val category: String,
    val images: List<String>
)
