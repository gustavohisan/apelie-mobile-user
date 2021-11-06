package com.gustavohisan.apelieuser.api.model.cart

internal data class AddCartItemsData(
    val productId: Int,
    val description: String,
    val quantity: Int
)
