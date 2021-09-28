package com.gustavohisan.apelieuser.api.model.store

import com.google.gson.annotations.SerializedName

internal data class Owner(
    @SerializedName("userId")
    val id: Int,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("photoUrl")
    val photoUrl: String?
)
