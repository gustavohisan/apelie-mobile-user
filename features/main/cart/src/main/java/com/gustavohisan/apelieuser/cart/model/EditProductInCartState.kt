package com.gustavohisan.apelieuser.cart.model

internal sealed class EditProductInCartState {

    object Success : EditProductInCartState()

    object Error : EditProductInCartState()

    object Loading : EditProductInCartState()

    object None : EditProductInCartState()
}
