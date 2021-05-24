package com.gustavohisan.apelieuser.repository.repository.store

import com.gustavohisan.apelieuser.domain.model.store.StoreState
import com.gustavohisan.apelieuser.domain.repository.store.StoreRepository
import com.gustavohisan.apelieuser.repository.datasource.store.StoreDataSource
import com.gustavohisan.apelieuser.repository.mapper.store.StoreStateMapper

/**
 * Implementation of [StoreRepository].
 *
 * @param storeDataSource data source used for store API requests
 * @param storeStateMapper mapper used to map the store
 */
internal class StoreRepositoryImpl(
    private val storeDataSource: StoreDataSource,
    private val storeStateMapper: StoreStateMapper
) : StoreRepository {

    override suspend fun getMainScreenStores(): StoreState =
        storeStateMapper.toDomain(storeDataSource.getMainScreenStoreList())
}
