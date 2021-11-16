package com.gustavohisan.apelieuser.api.model.address

internal data class EditAddressData(
    val addressId: Int,
    val city: String,
    val complement: String,
    val district: String,
    val number: String,
    val state: String,
    val street: String,
    val zipCode: String
)
