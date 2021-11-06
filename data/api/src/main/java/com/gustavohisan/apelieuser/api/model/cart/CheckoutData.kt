package com.gustavohisan.apelieuser.api.model.cart

internal data class CheckoutData(
    val paymentMethod: String,
    val addressId: Int
)
