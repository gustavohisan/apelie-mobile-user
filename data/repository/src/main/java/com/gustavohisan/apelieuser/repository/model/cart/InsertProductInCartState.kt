package com.gustavohisan.apelieuser.repository.model.cart

sealed class InsertProductInCartState {

    object Success : InsertProductInCartState()

    object Error : InsertProductInCartState()
}
