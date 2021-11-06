package com.gustavohisan.apelieuser.api.model.cart

internal sealed class EditProductInCartState {

    object Success : EditProductInCartState()

    object Error : EditProductInCartState()
}
