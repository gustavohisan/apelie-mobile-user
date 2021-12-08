package com.gustavohisan.apelieuser.orders.model

internal data class Order(
    val orderId: Int,
    val trackingCode: String,
    val status: String,
    val paymentMethod: String,
    val createdAt: String,
    val totalValue: Float,
    val store: Store,
    val itemList: List<ItemList>,
    val address: Address,
    val hasReview: Boolean
)
