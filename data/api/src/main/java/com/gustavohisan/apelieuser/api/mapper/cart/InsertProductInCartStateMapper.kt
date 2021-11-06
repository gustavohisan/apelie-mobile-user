package com.gustavohisan.apelieuser.api.mapper.cart

import com.gustavohisan.apelieuser.api.model.cart.InsertProductInCartState as ApiState
import com.gustavohisan.apelieuser.repository.model.cart.InsertProductInCartState as RepoState

internal class InsertProductInCartStateMapper {

    fun toRepo(apiState: ApiState): RepoState =
        when (apiState) {
            is ApiState.Success -> RepoState.Success
            is ApiState.Error -> RepoState.Error
        }
}
