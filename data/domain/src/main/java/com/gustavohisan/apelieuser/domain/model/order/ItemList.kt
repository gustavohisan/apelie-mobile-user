package com.gustavohisan.apelieuser.domain.model.order

import com.gustavohisan.apelieuser.domain.model.store.Product

data class ItemList(
    val product: Product,
    val description: String,
    val quantity: Int
)
