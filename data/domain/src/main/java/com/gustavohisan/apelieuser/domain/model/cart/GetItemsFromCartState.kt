package com.gustavohisan.apelieuser.domain.model.cart

sealed class GetItemsFromCartState {

    data class Success(val itemList: List<CartItem>) : GetItemsFromCartState()

    object Error : GetItemsFromCartState()
}
