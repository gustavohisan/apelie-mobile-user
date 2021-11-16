package com.gustavohisan.apelieuser.checkout.model

internal sealed class CheckoutItemsFromCartState {

    object None : CheckoutItemsFromCartState()

    object Loading : CheckoutItemsFromCartState()

    object Success : CheckoutItemsFromCartState()

    object Error : CheckoutItemsFromCartState()
}
