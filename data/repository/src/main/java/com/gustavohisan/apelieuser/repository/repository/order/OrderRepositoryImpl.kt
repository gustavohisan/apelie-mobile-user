package com.gustavohisan.apelieuser.repository.repository.order

import com.gustavohisan.apelieuser.domain.model.order.GetOrderByIdState
import com.gustavohisan.apelieuser.domain.model.order.GetUserOrdersState
import com.gustavohisan.apelieuser.domain.repository.order.OrderRepository
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import com.gustavohisan.apelieuser.repository.mapper.order.GetOrderByIdStateMapper
import com.gustavohisan.apelieuser.repository.mapper.order.GetUserOrdersStateMapper

internal class OrderRepositoryImpl(
    private val userApiDataSource: UserApiDataSource,
    private val getUserOrdersStateMapper: GetUserOrdersStateMapper,
    private val getOrderByIdStateMapper: GetOrderByIdStateMapper
) : OrderRepository {

    override suspend fun getUserOrders(): GetUserOrdersState =
        getUserOrdersStateMapper.toDomain(userApiDataSource.getUserOrders())

    override suspend fun getOrderById(id: Int): GetOrderByIdState =
        getOrderByIdStateMapper.toDomain(userApiDataSource.getOrderById(id))

    override suspend fun rateOrder(id: Int, description: String, rating: Int): Boolean =
        userApiDataSource.rateOrder(id, rating, description)
}
