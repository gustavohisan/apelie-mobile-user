package com.gustavohisan.apelieuser.api.model.product

import com.google.gson.annotations.SerializedName

internal data class ProductImage(
    @SerializedName("url")
    val url: String
)
