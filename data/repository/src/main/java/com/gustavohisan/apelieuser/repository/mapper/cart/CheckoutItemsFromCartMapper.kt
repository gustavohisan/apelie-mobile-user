package com.gustavohisan.apelieuser.repository.mapper.cart

import com.gustavohisan.apelieuser.repository.model.cart.CheckoutItemsFromCartState as RepoState
import com.gustavohisan.apelieuser.domain.model.cart.CheckoutItemsFromCartState as DomainState

internal class CheckoutItemsFromCartMapper {

    fun toDomain(repoState: RepoState): DomainState =
        when (repoState) {
            is RepoState.Success -> DomainState.Success
            is RepoState.Error -> DomainState.Error
        }
}
