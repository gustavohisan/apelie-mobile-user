package com.gustavohisan.apelieuser.domain.usecase.order

import com.gustavohisan.apelieuser.domain.repository.order.OrderRepository

class RateOrder(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(id: Int, description: String, rating: Int): Boolean =
        orderRepository.rateOrder(id, description, rating)
}
