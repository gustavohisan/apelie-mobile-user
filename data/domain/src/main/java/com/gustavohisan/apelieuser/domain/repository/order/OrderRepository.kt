package com.gustavohisan.apelieuser.domain.repository.order

import com.gustavohisan.apelieuser.domain.model.order.GetOrderByIdState
import com.gustavohisan.apelieuser.domain.model.order.GetUserOrdersState

interface OrderRepository {

    suspend fun getUserOrders(): GetUserOrdersState

    suspend fun getOrderById(id: Int): GetOrderByIdState

    suspend fun rateOrder(id: Int, description: String, rating: Int): Boolean
}
