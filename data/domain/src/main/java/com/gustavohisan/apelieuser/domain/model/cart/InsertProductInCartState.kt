package com.gustavohisan.apelieuser.domain.model.cart

sealed class InsertProductInCartState {

    object Success : InsertProductInCartState()

    object Error : InsertProductInCartState()
}
