package com.gustavohisan.apelieuser.api.mapper.store

import com.gustavohisan.apelieuser.api.model.product.ProductState as ApiState
import com.gustavohisan.apelieuser.repository.model.store.ProductState as RepositoryState

internal class ProductStateMapper(private val productMapper: ProductMapper) {

    fun toRepository(apiState: ApiState): RepositoryState =
        when (apiState) {
            is ApiState.Success -> RepositoryState.Success(productMapper.toRepo(apiState.product))
            is ApiState.Error -> RepositoryState.Error
        }
}
