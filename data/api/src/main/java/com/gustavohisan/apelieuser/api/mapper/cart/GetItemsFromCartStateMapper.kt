package com.gustavohisan.apelieuser.api.mapper.cart

import com.gustavohisan.apelieuser.api.model.cart.GetItemsFromCartState as ApiState
import com.gustavohisan.apelieuser.repository.model.cart.GetItemsFromCartState as RepoState

internal class GetItemsFromCartStateMapper(private val cartItemMapper: CartItemMapper) {

    fun toRepo(apiState: ApiState): RepoState =
        when (apiState) {
            is ApiState.Success -> apiState.itemList?.let {
                RepoState.Success(it.map {
                    cartItemMapper.toRepo(
                        it
                    )
                })
            } ?: RepoState.Error
            is ApiState.Error -> RepoState.Error
        }
}
