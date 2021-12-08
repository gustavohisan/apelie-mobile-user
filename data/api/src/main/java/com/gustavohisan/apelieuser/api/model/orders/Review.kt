package com.gustavohisan.apelieuser.api.model.orders

import com.google.gson.annotations.SerializedName

internal data class Review(
    @SerializedName("description")
    val description: String,
    @SerializedName("rating")
    val rating: Float
)
