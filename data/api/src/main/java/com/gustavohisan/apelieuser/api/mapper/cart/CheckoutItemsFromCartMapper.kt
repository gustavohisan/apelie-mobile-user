package com.gustavohisan.apelieuser.api.mapper.cart

import com.gustavohisan.apelieuser.repository.model.cart.CheckoutItemsFromCartState as RepoState
import com.gustavohisan.apelieuser.api.model.cart.CheckoutItemsFromCartState as ApiState

internal class CheckoutItemsFromCartMapper {

    fun toRepo(apiState: ApiState): RepoState =
        when (apiState) {
            is ApiState.Success -> RepoState.Success
            is ApiState.Error -> RepoState.Error
        }
}
