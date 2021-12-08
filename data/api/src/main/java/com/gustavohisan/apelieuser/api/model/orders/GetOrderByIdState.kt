package com.gustavohisan.apelieuser.api.model.orders

internal sealed class GetOrderByIdState {

    data class Success(val order: Order) : GetOrderByIdState()

    object Error : GetOrderByIdState()
}
