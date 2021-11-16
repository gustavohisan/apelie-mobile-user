package com.gustavohisan.apelieuser.repository.model.order

sealed class GetUserOrdersState {

    data class Success(val orders: List<Order>) : GetUserOrdersState()

    object Error : GetUserOrdersState()
}
