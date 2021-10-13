package com.gustavohisan.apelieuser.repository.mapper.store

import com.gustavohisan.apelieuser.domain.model.store.MainScreenStoreErrorType
import com.gustavohisan.apelieuser.domain.model.store.MainScreenStoreState as DomainStoreState
import com.gustavohisan.apelieuser.domain.model.store.SearchStoreState
import com.gustavohisan.apelieuser.repository.model.store.MainScreenStoreState as RepositoryStoreState

/**
 * Mapper used to map the store state from repository to domain representation.
 *
 * @param storeMapper mapper used to map a store
 */
internal class MainScreenStoreStateMapper(private val storeMapper: MainScreenStoreMapper) {

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
                DomainStoreState.Error(MainScreenStoreErrorType.SERVER_UNAVAILABLE)
        }

    fun toDomainSearch(repositoryStoreState: RepositoryStoreState): SearchStoreState =
        when (repositoryStoreState) {
            is RepositoryStoreState.Success ->
                SearchStoreState.Success(
                    repositoryStoreState.storeList?.map { store ->
                        storeMapper.toDomain(store)
                    }
                )
            is RepositoryStoreState.Error ->
                SearchStoreState.Error(MainScreenStoreErrorType.SERVER_UNAVAILABLE)
        }
}
