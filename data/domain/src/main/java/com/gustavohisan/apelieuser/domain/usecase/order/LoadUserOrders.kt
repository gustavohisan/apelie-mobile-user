package com.gustavohisan.apelieuser.domain.usecase.order

import com.gustavohisan.apelieuser.domain.model.order.GetUserOrdersState
import com.gustavohisan.apelieuser.domain.model.order.Order
import com.gustavohisan.apelieuser.domain.repository.order.OrderRepository

class LoadUserOrders(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(): GetUserOrdersState {
        val state = orderRepository.getUserOrders()
        return if (state is GetUserOrdersState.Success) {
            checkIfOrdersAreEmpty(state.orders)
        } else {
            state
        }
    }

    private fun checkIfOrdersAreEmpty(orders: List<Order>) =
        if (orders.isEmpty()) {
            GetUserOrdersState.Empty
        } else {
            GetUserOrdersState.Success(orders)
        }
}
