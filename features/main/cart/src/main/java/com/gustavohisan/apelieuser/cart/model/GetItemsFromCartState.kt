package com.gustavohisan.apelieuser.cart.model

internal sealed class GetItemsFromCartState {

    data class Success(val itemList: List<CartItem>) : GetItemsFromCartState()

    object Error : GetItemsFromCartState()

    object Loading : GetItemsFromCartState()
}
