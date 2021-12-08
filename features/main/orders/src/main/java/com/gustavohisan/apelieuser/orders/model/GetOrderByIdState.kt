package com.gustavohisan.apelieuser.orders.model

internal sealed class GetOrderByIdState {

    data class Success(val order: Order) : GetOrderByIdState()

    object Error : GetOrderByIdState()

    object Loading : GetOrderByIdState()
}
