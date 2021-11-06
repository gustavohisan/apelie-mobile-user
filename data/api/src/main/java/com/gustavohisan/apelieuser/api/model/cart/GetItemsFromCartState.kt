package com.gustavohisan.apelieuser.api.model.cart

internal sealed class GetItemsFromCartState {

    data class Success(val itemList: List<CartItem>?) : GetItemsFromCartState()

    object Error : GetItemsFromCartState()
}
