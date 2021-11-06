package com.gustavohisan.apelieuser.cart.model

internal data class CartItem(
    val id: Int,
    val product: Product,
    val description: String,
    val quantity: Int,
)
