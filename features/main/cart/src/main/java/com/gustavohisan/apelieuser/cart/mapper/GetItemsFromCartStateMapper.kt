package com.gustavohisan.apelieuser.cart.mapper

import com.gustavohisan.apelieuser.domain.model.cart.GetItemsFromCartState as DomainState
import com.gustavohisan.apelieuser.cart.model.GetItemsFromCartState as PresentationState

internal class GetItemsFromCartStateMapper(private val cartItemMapper: CartItemMapper) {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success(domainState.itemList.map {
                cartItemMapper.toPresentation(
                    it
                )
            })
            is DomainState.Error -> PresentationState.Error
        }
}
