package com.gustavohisan.apelieuser.domain.model.cart

sealed class EditProductInCartState {

    object Success : EditProductInCartState()

    object Error : EditProductInCartState()
}
