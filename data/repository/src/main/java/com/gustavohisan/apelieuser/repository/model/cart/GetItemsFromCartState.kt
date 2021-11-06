package com.gustavohisan.apelieuser.repository.model.cart

sealed class GetItemsFromCartState {

    data class Success(val itemList: List<CartItem>) : GetItemsFromCartState()

    object Error : GetItemsFromCartState()
}
