package com.gustavohisan.apelieuser.api.model.cart

import com.google.gson.annotations.SerializedName
import com.gustavohisan.apelieuser.api.model.product.Product

internal data class CartItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("product")
    val product: Product,
    @SerializedName("description")
    val description: String,
    @SerializedName("quantity")
    val quantity: Int,
)
