package com.gustavohisan.apelieuser.repository.mapper.order

import com.gustavohisan.apelieuser.domain.model.order.GetUserOrdersState as DomainState
import com.gustavohisan.apelieuser.repository.model.order.GetUserOrdersState as RepoState

internal class GetUserOrdersStateMapper(private val orderMapper: OrderMapper) {

    fun toDomain(repoState: RepoState): DomainState =
        when (repoState) {
            is RepoState.Success -> DomainState.Success(repoState.orders.map {
                orderMapper.toDomain(
                    it
                )
            })
            is RepoState.Error -> DomainState.Error
        }
}
