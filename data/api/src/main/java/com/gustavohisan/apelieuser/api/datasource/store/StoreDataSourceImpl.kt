package com.gustavohisan.apelieuser.api.datasource.store

import com.gustavohisan.apelieuser.api.endpoints.store.StoreEndpoints
import com.gustavohisan.apelieuser.api.mapper.store.MainScreenStoreStateMapper
import com.gustavohisan.apelieuser.api.model.store.MainScreenStoreState
import com.gustavohisan.apelieuser.api.factory.ApiFactory
import com.gustavohisan.apelieuser.api.mapper.store.ProductStateMapper
import com.gustavohisan.apelieuser.api.mapper.store.StoreStateMapper
import com.gustavohisan.apelieuser.api.model.product.ProductState
import com.gustavohisan.apelieuser.api.model.store.StoreState
import com.gustavohisan.apelieuser.repository.datasource.store.StoreDataSource
import com.gustavohisan.apelieuser.repository.model.store.CategoryState
import com.gustavohisan.apelieuser.repository.model.store.ProductState as RepoProductState
import timber.log.Timber
import com.gustavohisan.apelieuser.repository.model.store.MainScreenStoreState as RepositoryMainScreenStoreState
import com.gustavohisan.apelieuser.repository.model.store.StoreState as RepositoryStoreState

/**
 * Implementation of [StoreDataSource].
 *
 * @param apiFactory factory used to provide the retrofit instance
 */
internal class StoreDataSourceImpl(
    apiFactory: ApiFactory,
    private val mainScreenStoreStateMapper: MainScreenStoreStateMapper,
    private val storeStateMapper: StoreStateMapper,
    private val productStateMapper: ProductStateMapper
) : StoreDataSource {

    private val endpoint = apiFactory.getRetrofitInstance().create(StoreEndpoints::class.java)

    override suspend fun getMainScreenStoreList(): RepositoryMainScreenStoreState {
        val callback = endpoint.getFrontPageStores()
        return if (callback.isSuccessful) {
            mainScreenStoreStateMapper.toRepository(MainScreenStoreState.Success(callback.body()))
        } else {
            mainScreenStoreStateMapper.toRepository(MainScreenStoreState.Error)
        }
    }

    override suspend fun getStoreData(storeId: Int): RepositoryStoreState {
        val callback = endpoint.getStoreData(storeId)
        val body = callback.body()
        return if (callback.isSuccessful && body != null) {
            Timber.d("getStoreData - Success - store = $body")
            storeStateMapper.toRepo(StoreState.Success(body))
        } else {
            Timber.e("getStoreData - Error")
            storeStateMapper.toRepo(StoreState.Error)
        }
    }

    override suspend fun searchStores(
        query: String,
        categories: String?
    ): RepositoryMainScreenStoreState {
        val callback = endpoint.searchStores(query, categories)
        return if (callback.isSuccessful) {
            mainScreenStoreStateMapper.toRepository(MainScreenStoreState.Success(callback.body()))
        } else {
            mainScreenStoreStateMapper.toRepository(MainScreenStoreState.Error)
        }
    }

    override suspend fun getProductData(productId: Int): RepoProductState {
        val callback = endpoint.getProductData(productId)
        val body = callback.body()
        return if (callback.isSuccessful && body != null) {
            Timber.d("getProductData - Success - product = $body")
            productStateMapper.toRepository(ProductState.Success(body))
        } else {
            Timber.e("getProductData - Error")
            productStateMapper.toRepository(ProductState.Error)
        }
    }

    override suspend fun getCategories(): CategoryState {
        val callback = endpoint.getCategories()
        val body = callback.body()
        return if (callback.isSuccessful && body != null && body.isNotEmpty()) {
            Timber.d("getCategories - Success - categories = $body")
            CategoryState.Success(body)
        } else {
            Timber.e("getCategories - Error")
            CategoryState.Error
        }
    }
}
