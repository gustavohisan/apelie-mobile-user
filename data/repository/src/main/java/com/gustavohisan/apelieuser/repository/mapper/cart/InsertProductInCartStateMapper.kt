package com.gustavohisan.apelieuser.repository.mapper.cart

import com.gustavohisan.apelieuser.repository.model.cart.InsertProductInCartState as RepoState
import com.gustavohisan.apelieuser.domain.model.cart.InsertProductInCartState as DomainState

internal class InsertProductInCartStateMapper {

    fun toDomain(repoState: RepoState): DomainState =
        when (repoState) {
            is RepoState.Success -> DomainState.Success
            is RepoState.Error -> DomainState.Error
        }
}
