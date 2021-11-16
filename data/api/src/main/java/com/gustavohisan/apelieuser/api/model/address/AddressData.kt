package com.gustavohisan.apelieuser.api.model.address

internal data class AddressData(
    val city: String,
    val complement: String,
    val district: String,
    val number: String,
    val state: String,
    val street: String,
    val zipCode: String
)
