package com.gustavohisan.apelieuser.api.model.orders

import com.google.gson.annotations.SerializedName
import com.gustavohisan.apelieuser.api.model.store.Store

internal data class Order(
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("trackingCode")
    val trackingCode: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("paymentMethod")
    val paymentMethod: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("totalValue")
    val totalValue: Float,
    @SerializedName("store")
    val store: Store,
    @SerializedName("itemList")
    val itemList: List<ItemList>
)
