package com.gustavohisan.apelieuser.api.model.cart

internal data class EditCartItemsData(
    val cartItemId: Int,
    val description: String,
    val quantity: Int
)
