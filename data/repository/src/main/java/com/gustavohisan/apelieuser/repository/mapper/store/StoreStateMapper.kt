package com.gustavohisan.apelieuser.repository.mapper.store

import com.gustavohisan.apelieuser.domain.model.store.StoreErrorType
import com.gustavohisan.apelieuser.domain.model.store.StoreState as DomainStoreState
import com.gustavohisan.apelieuser.repository.model.store.StoreState as RepositoryStoreState

internal class StoreStateMapper(private val storeMapper: StoreMapper) {

    fun toDomain(repositoryStoreState: RepositoryStoreState): DomainStoreState =
        when (repositoryStoreState) {
            is RepositoryStoreState.Success ->
                DomainStoreState.Success(storeMapper.toDomain(repositoryStoreState.store))
            is RepositoryStoreState.Error ->
                DomainStoreState.Error(StoreErrorType.SERVER_UNAVAILABLE)
        }
}
