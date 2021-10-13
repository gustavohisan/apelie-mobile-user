package com.gustavohisan.apelieuser.repository.mapper.store

import com.gustavohisan.apelieuser.domain.model.store.ProductErrorType
import com.gustavohisan.apelieuser.repository.model.store.ProductState as RepoState
import com.gustavohisan.apelieuser.domain.model.store.ProductState as DomainState

internal class ProductStateMapper(private val productMapper: ProductMapper) {

    fun toDomain(repoState: RepoState): DomainState =
        when (repoState) {
            is RepoState.Success -> DomainState.Success(productMapper.toDomain(repoState.product))
            is RepoState.Error -> DomainState.Error(ProductErrorType.SERVER_UNAVAILABLE)
        }
}
