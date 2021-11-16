package com.gustavohisan.apelieuser.domain.repository.order

import com.gustavohisan.apelieuser.domain.model.order.GetUserOrdersState

interface OrderRepository {

    suspend fun getUserOrders(): GetUserOrdersState
}
