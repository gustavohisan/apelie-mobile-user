package com.gustavohisan.apelieuser.repository.mapper.cart

import com.gustavohisan.apelieuser.repository.model.cart.EditProductInCartState as RepoState
import com.gustavohisan.apelieuser.domain.model.cart.EditProductInCartState as DomainState

internal class EditProductInCartStateMapper {

    fun toDomain(repoState: RepoState): DomainState =
        when (repoState) {
            is RepoState.Success -> DomainState.Success
            is RepoState.Error -> DomainState.Error
        }
}
