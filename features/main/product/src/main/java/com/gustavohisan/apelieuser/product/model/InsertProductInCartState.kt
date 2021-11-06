package com.gustavohisan.apelieuser.product.model

internal sealed class InsertProductInCartState {

    object Success : InsertProductInCartState()

    object Error : InsertProductInCartState()

    object Loading : InsertProductInCartState()

    object NotInserting : InsertProductInCartState()
}
