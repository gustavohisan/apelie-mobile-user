package com.gustavohisan.apelieuser.api.mapper.order

import com.gustavohisan.apelieuser.api.model.orders.GetOrderByIdState as ApiState
import com.gustavohisan.apelieuser.repository.model.order.GetOrderByIdState as RepoState

internal class GetOrderByIdStateMapper(private val orderMapper: OrderMapper) {

    fun toRepo(apiState: ApiState): RepoState =
        when (apiState) {
            is ApiState.Success -> RepoState.Success(orderMapper.toRepo(apiState.order))
            is ApiState.Error -> RepoState.Error
        }
}
