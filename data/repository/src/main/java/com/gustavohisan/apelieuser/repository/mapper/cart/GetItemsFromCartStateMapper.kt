package com.gustavohisan.apelieuser.repository.mapper.cart

import com.gustavohisan.apelieuser.domain.model.cart.GetItemsFromCartState as DomainState
import com.gustavohisan.apelieuser.repository.model.cart.GetItemsFromCartState as RepoState

internal class GetItemsFromCartStateMapper(private val cartItemMapper: CartItemMapper) {

    fun toDomain(repoState: RepoState): DomainState =
        when (repoState) {
            is RepoState.Success -> DomainState.Success(repoState.itemList.map {
                cartItemMapper.toRepo(
                    it
                )
            })
            is RepoState.Error -> DomainState.Error
        }
}
