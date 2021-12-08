package com.gustavohisan.apelieuser.domain.model.order

import com.gustavohisan.apelieuser.domain.model.address.Address
import com.gustavohisan.apelieuser.domain.model.store.Store

data class Order(
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
