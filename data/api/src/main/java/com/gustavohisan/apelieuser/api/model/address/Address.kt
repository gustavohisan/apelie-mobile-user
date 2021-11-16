package com.gustavohisan.apelieuser.api.model.address

import com.google.gson.annotations.SerializedName

internal data class Address(
    @SerializedName("addressId")
    val addressId: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("complement")
    val complement: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("zipCode")
    val zipCode: String
)
