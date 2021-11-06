package com.gustavohisan.apelieuser.product.mapper

import com.gustavohisan.apelieuser.domain.model.cart.InsertProductInCartState as DomainState
import com.gustavohisan.apelieuser.product.model.InsertProductInCartState as PresentationState

internal class InsertProductInCartStateMapper {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success
            is DomainState.Error -> PresentationState.Error
        }
}
