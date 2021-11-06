package com.gustavohisan.apelieuser.repository.model.cart

import com.gustavohisan.apelieuser.repository.model.store.Product

data class CartItem(
    val id: Int,
    val product: Product,
    val description: String,
    val quantity: Int,
)
