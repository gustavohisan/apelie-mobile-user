package com.gustavohisan.apelieuser.domain.model.cart

import com.gustavohisan.apelieuser.domain.model.store.Product

data class CartItem(
    val id: Int,
    val product: Product,
    val description: String,
    val quantity: Int,
)
