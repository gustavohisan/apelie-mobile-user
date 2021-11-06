package com.gustavohisan.apelieuser.api.mapper.cart

import com.gustavohisan.apelieuser.repository.model.cart.EditProductInCartState as RepoState
import com.gustavohisan.apelieuser.api.model.cart.EditProductInCartState as ApiState

internal class EditProductInCartStateMapper {

    fun toRepo(apiState: ApiState): RepoState =
        when (apiState) {
            is ApiState.Success -> RepoState.Success
            is ApiState.Error -> RepoState.Error
        }
}
