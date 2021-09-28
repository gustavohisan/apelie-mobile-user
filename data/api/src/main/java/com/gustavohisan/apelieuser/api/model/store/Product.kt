package com.gustavohisan.apelieuser.api.model.store

import com.google.gson.annotations.SerializedName

internal data class Product(
    @SerializedName("productId")
    val id: Int,
    @SerializedName("price")
    val price: Float,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("images")
    val images: List<ProductImage>
)
