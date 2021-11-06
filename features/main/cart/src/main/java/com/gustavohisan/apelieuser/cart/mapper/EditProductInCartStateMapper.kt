package com.gustavohisan.apelieuser.cart.mapper

import com.gustavohisan.apelieuser.domain.model.cart.EditProductInCartState as DomainState
import com.gustavohisan.apelieuser.cart.model.EditProductInCartState as PresentationState

internal class EditProductInCartStateMapper {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success
            is DomainState.Error -> PresentationState.Error
        }
}
