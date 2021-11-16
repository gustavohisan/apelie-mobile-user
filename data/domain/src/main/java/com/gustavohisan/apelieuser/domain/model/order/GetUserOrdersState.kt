package com.gustavohisan.apelieuser.domain.model.order

sealed class GetUserOrdersState {

    data class Success(val orders: List<Order>) : GetUserOrdersState()

    object Empty : GetUserOrdersState()

    object Error : GetUserOrdersState()
}
