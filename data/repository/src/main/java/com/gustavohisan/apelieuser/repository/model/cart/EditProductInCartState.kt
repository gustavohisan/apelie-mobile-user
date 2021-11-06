package com.gustavohisan.apelieuser.repository.model.cart

sealed class EditProductInCartState {

    object Success : EditProductInCartState()

    object Error : EditProductInCartState()
}
