package com.gustavohisan.apelieuser.orders.mapper

import com.gustavohisan.apelieuser.orders.model.GetOrderByIdState as PresentationState
import com.gustavohisan.apelieuser.domain.model.order.GetOrderByIdState as DomainState

internal class GetOrderByIdStateMapper(private val orderMapper: OrderMapper) {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success(
                orderMapper.toPresentation(
                    domainState.order
                )
            )
            is DomainState.Error -> PresentationState.Error
        }
}
