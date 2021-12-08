package com.gustavohisan.apelieuser.repository.mapper.order

import com.gustavohisan.apelieuser.domain.model.order.GetOrderByIdState as DomainState
import com.gustavohisan.apelieuser.repository.model.order.GetOrderByIdState as RepoState

internal class GetOrderByIdStateMapper(private val orderMapper: OrderMapper) {

    fun toDomain(repoState: RepoState): DomainState =
        when (repoState) {
            is RepoState.Success -> DomainState.Success(orderMapper.toDomain(repoState.order))
            is RepoState.Error -> DomainState.Error
        }
}
