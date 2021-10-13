package com.gustavohisan.apelieuser.repository.repository.store

import com.gustavohisan.apelieuser.domain.model.store.*
import com.gustavohisan.apelieuser.domain.repository.store.StoreRepository
import com.gustavohisan.apelieuser.repository.datasource.store.StoreDataSource
import com.gustavohisan.apelieuser.repository.mapper.store.CategoryStateMapper
import com.gustavohisan.apelieuser.repository.mapper.store.MainScreenStoreStateMapper
import com.gustavohisan.apelieuser.repository.mapper.store.ProductStateMapper
import com.gustavohisan.apelieuser.repository.mapper.store.StoreStateMapper

/**
 * Implementation of [StoreRepository].
 *
 * @param storeDataSource data source used for store API requests
 */
internal class StoreRepositoryImpl(
    private val storeDataSource: StoreDataSource,
    private val mainScreenStoreStateMapper: MainScreenStoreStateMapper,
    private val storeStateMapper: StoreStateMapper,
    private val productStateMapper: ProductStateMapper,
    private val categoryStateMapper: CategoryStateMapper
) : StoreRepository {

    override suspend fun getMainScreenStores(): MainScreenStoreState =
        mainScreenStoreStateMapper.toDomain(storeDataSource.getMainScreenStoreList())

    override suspend fun getStoreData(storeId: Int): StoreState =
        storeStateMapper.toDomain(storeDataSource.getStoreData(storeId))

    override suspend fun searchStores(query: String, categories: String?): SearchStoreState =
        mainScreenStoreStateMapper.toDomainSearch(storeDataSource.searchStores(query, categories))

    override suspend fun getProductData(productId: Int): ProductState =
        productStateMapper.toDomain(storeDataSource.getProductData(productId))

    override suspend fun getCategories(): CategoryState =
        categoryStateMapper.toDomain(storeDataSource.getCategories())
}
