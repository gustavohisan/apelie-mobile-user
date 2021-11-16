package com.gustavohisan.apelieuser.api.mapper.order

import com.gustavohisan.apelieuser.api.model.orders.GetUserOrdersState as ApiState
import com.gustavohisan.apelieuser.repository.model.order.GetUserOrdersState as RepoState

internal class GetUserOrdersStateMapper(private val orderMapper: OrderMapper) {

    fun toRepo(apiState: ApiState): RepoState =
        when (apiState) {
            is ApiState.Success -> RepoState.Success(apiState.orders.map {
                orderMapper.toRepo(
                    it
                )
            })
            is ApiState.Error -> RepoState.Error
        }
}
