package com.gustavohisan.apelieuser.api.datasource.store

import com.gustavohisan.apelieuser.api.endpoints.store.StoreEndpoints
import com.gustavohisan.apelieuser.api.mapper.store.StoreStateMapper
import com.gustavohisan.apelieuser.api.model.store.StoreState
import com.gustavohisan.apelieuser.api.factory.ApiFactory
import com.gustavohisan.apelieuser.repository.datasource.store.StoreDataSource
import com.gustavohisan.apelieuser.repository.model.store.StoreState as RepositoryStoreState

/**
 * Implementation of [StoreDataSource].
 *
 * @param apiFactory factory used to provide the retrofit instance
 * @param storeStateMapper mapper used to map the store
 */
internal class StoreDataSourceImpl(
    apiFactory: ApiFactory,
    private val storeStateMapper: StoreStateMapper
) : StoreDataSource {

    private val endpoint = apiFactory.getRetrofitInstance().create(StoreEndpoints::class.java)

    override suspend fun getMainScreenStoreList(): RepositoryStoreState {
        val callback = endpoint.getFrontPageStores()
        return if (callback.isSuccessful) {
            storeStateMapper.toRepository(StoreState.Success(callback.body()))
        } else {
            storeStateMapper.toRepository(StoreState.Error)
        }
    }
}
