package com.gustavohisan.apelieuser.checkout.mapper

import com.gustavohisan.apelieuser.domain.model.cart.CheckoutItemsFromCartState as DomainState
import com.gustavohisan.apelieuser.checkout.model.CheckoutItemsFromCartState as PresentationState

internal class CheckoutItemsFromCartStateMapper {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success
            is DomainState.Error -> PresentationState.Error
        }
}
