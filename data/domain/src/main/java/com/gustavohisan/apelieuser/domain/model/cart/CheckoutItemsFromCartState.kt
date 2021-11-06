package com.gustavohisan.apelieuser.domain.model.cart

sealed class CheckoutItemsFromCartState {

    object Success : CheckoutItemsFromCartState()

    object Error : CheckoutItemsFromCartState()
}
