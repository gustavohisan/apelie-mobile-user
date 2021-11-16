package com.gustavohisan.apelieuser.repository.repository.order

import com.gustavohisan.apelieuser.domain.model.order.GetUserOrdersState
import com.gustavohisan.apelieuser.domain.repository.order.OrderRepository
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import com.gustavohisan.apelieuser.repository.mapper.order.GetUserOrdersStateMapper

internal class OrderRepositoryImpl(
    private val userApiDataSource: UserApiDataSource,
    private val getUserOrdersStateMapper: GetUserOrdersStateMapper
) : OrderRepository {

    override suspend fun getUserOrders(): GetUserOrdersState =
        getUserOrdersStateMapper.toDomain(userApiDataSource.getUserOrders())
}
