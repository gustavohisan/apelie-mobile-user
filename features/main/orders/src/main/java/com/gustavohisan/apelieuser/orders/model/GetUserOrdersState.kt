package com.gustavohisan.apelieuser.orders.model

internal sealed class GetUserOrdersState {

    data class Success(val orders: List<Order>) : GetUserOrdersState()

    object Empty : GetUserOrdersState()

    object Error : GetUserOrdersState()

    object Loading : GetUserOrdersState()
}
