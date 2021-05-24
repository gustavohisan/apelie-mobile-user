package com.gustavohisan.apelieuser.repository.mapper.store

import com.gustavohisan.apelieuser.domain.model.store.StoreErrorType
import com.gustavohisan.apelieuser.domain.model.store.StoreState as DomainStoreState
import com.gustavohisan.apelieuser.repository.model.store.StoreState as RepositoryStoreState

/**
 * Mapper used to map the store state from repository to domain representation.
 *
 * @param storeMapper mapper used to map a store
 */
internal class StoreStateMapper(private val storeMapper: StoreMapper) {

    /**
     * Maps a [RepositoryStoreState] to a [DomainStoreState].
     *
     * @param repositoryStoreState the [RepositoryStoreState] to be mapped
     *
     * @return the mapped [DomainStoreState]
     */
    fun toDomain(repositoryStoreState: RepositoryStoreState): DomainStoreState =
        when (repositoryStoreState) {
            is RepositoryStoreState.Success ->
                DomainStoreState.Success(
                    repositoryStoreState.storeList?.map { store ->
                        storeMapper.toDomain(store)
                    }
                )
            is RepositoryStoreState.Error ->
                DomainStoreState.Error(StoreErrorType.SERVER_UNAVAILABLE)
        }
}