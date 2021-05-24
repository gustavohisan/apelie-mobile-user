package com.gustavohisan.apelieuser.api.mapper.store

import com.gustavohisan.apelieuser.api.model.store.StoreState as ApiStoreState
import com.gustavohisan.apelieuser.repository.model.store.StoreState as RepositoryStoreState

/**
 * Mapper used to map the store state from api to repository representation.
 *
 * @param storeMapper mapper used to map a store
 */
internal class StoreStateMapper(private val storeMapper: StoreMapper) {

    /**
     * Maps a [ApiStoreState] to a [RepositoryStoreState].
     *
     * @param apiStoreState the [ApiStoreState] to be mapped
     *
     * @return the mapped [RepositoryStoreState]
     */
    fun toRepository(apiStoreState: ApiStoreState): RepositoryStoreState =
        when (apiStoreState) {
            is ApiStoreState.Success ->
                RepositoryStoreState.Success(
                    apiStoreState.storeList?.map { store ->
                        storeMapper.toRepository(store)
                    }
                )
            is ApiStoreState.Error ->
                RepositoryStoreState.Error
        }
}
