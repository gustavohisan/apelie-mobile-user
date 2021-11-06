package com.gustavohisan.apelieuser.repository.model.cart

sealed class CheckoutItemsFromCartState {

    object Success : CheckoutItemsFromCartState()

    object Error : CheckoutItemsFromCartState()
}
