package com.gustavohisan.apelieuser.api.model.cart

internal sealed class CheckoutItemsFromCartState {

    object Success : CheckoutItemsFromCartState()

    object Error : CheckoutItemsFromCartState()
}
