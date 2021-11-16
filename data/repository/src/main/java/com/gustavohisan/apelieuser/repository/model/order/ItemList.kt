package com.gustavohisan.apelieuser.repository.model.order

import com.gustavohisan.apelieuser.repository.model.store.Product

data class ItemList(
    val product: Product,
    val description: String,
    val quantity: Int
)
