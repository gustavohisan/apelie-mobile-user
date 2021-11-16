package com.gustavohisan.apelieuser.repository.model.order

import com.gustavohisan.apelieuser.repository.model.store.Store

data class Order(
    val orderId: Int,
    val trackingCode: String,
    val status: String,
    val paymentMethod: String,
    val createdAt: String,
    val totalValue: Float,
    val store: Store,
    val itemList: List<ItemList>
)
