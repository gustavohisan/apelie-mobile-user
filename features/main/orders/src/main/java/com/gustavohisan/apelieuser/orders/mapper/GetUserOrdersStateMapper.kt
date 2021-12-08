package com.gustavohisan.apelieuser.orders.mapper

import com.gustavohisan.apelieuser.orders.model.GetUserOrdersState as PresentationState
import com.gustavohisan.apelieuser.domain.model.order.GetUserOrdersState as DomainState

internal class GetUserOrdersStateMapper(private val orderMapper: OrderMapper) {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success(domainState.orders.map {
                orderMapper.toPresentation(
                    it
                )
            })
            is DomainState.Error -> PresentationState.Error
            is DomainState.Empty -> PresentationState.Empty
        }
}
