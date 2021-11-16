package com.gustavohisan.apelieuser.domain.model.address

data class Address(
    val addressId: Int,
    val city: String,
    val complement: String,
    val district: String,
    val number: String,
    val state: String,
    val street: String,
    val zipCode: String
)
