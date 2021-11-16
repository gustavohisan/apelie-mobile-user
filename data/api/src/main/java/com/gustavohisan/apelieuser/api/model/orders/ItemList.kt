package com.gustavohisan.apelieuser.api.model.orders

import com.google.gson.annotations.SerializedName
import com.gustavohisan.apelieuser.api.model.product.Product

internal data class ItemList(
    @SerializedName("product")
    val product: Product,
    @SerializedName("description")
    val description: String,
    @SerializedName("quantity")
    val quantity: Int
)
