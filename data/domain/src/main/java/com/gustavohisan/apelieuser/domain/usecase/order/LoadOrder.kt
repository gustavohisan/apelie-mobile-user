package com.gustavohisan.apelieuser.domain.usecase.order

import com.gustavohisan.apelieuser.domain.model.order.GetOrderByIdState
import com.gustavohisan.apelieuser.domain.repository.order.OrderRepository

class LoadOrder(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(id: Int): GetOrderByIdState = orderRepository.getOrderById(id)
}
