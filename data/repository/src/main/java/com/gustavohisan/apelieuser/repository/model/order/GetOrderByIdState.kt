package com.gustavohisan.apelieuser.repository.model.order

sealed class GetOrderByIdState {

    data class Success(val order: Order) : GetOrderByIdState()

    object Error : GetOrderByIdState()
}
