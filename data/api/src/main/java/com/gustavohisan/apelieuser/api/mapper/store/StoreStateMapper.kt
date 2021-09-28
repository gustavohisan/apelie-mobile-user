package com.gustavohisan.apelieuser.api.mapper.store

import com.gustavohisan.apelieuser.api.model.store.StoreState as ApiStoreState
import com.gustavohisan.apelieuser.repository.model.store.StoreState as RepositoryStoreState

internal class StoreStateMapper(private val storeMapper: StoreMapper) {

    fun toRepo(apiStoreState: ApiStoreState): RepositoryStoreState =
        when (apiStoreState) {
            is ApiStoreState.Success ->
                RepositoryStoreState.Success(storeMapper.toRepo(apiStoreState.storeData))
            is ApiStoreState.Error ->
                RepositoryStoreState.Error
        }
}
