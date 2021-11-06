package com.gustavohisan.apelieuser.api.model.cart

internal sealed class InsertProductInCartState {

    object Success : InsertProductInCartState()

    object Error : InsertProductInCartState()
}
