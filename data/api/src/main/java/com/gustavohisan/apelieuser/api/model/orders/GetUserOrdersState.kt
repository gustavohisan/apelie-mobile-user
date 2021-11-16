package com.gustavohisan.apelieuser.api.model.orders

internal sealed class GetUserOrdersState {

    data class Success(val orders: List<Order>) : GetUserOrdersState()

    object Error : GetUserOrdersState()
}
