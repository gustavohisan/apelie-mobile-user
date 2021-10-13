package com.gustavohisan.apelieuser.repository.datasource.store

import com.gustavohisan.apelieuser.repository.model.store.CategoryState
import com.gustavohisan.apelieuser.repository.model.store.MainScreenStoreState
import com.gustavohisan.apelieuser.repository.model.store.ProductState
import com.gustavohisan.apelieuser.repository.model.store.StoreState

/**
 * Data source used in stores API requests.
 */
interface StoreDataSource {

    /**
     * Returns the store list to be shown on the home screen.
     *
     * @return the [MainScreenStoreState] with the request state
     */
    suspend fun getMainScreenStoreList(): MainScreenStoreState

    suspend fun getStoreData(storeId: Int): StoreState

    suspend fun searchStores(query: String, categories: String?): MainScreenStoreState

    suspend fun getProductData(productId: Int): ProductState

    suspend fun getCategories(): CategoryState
}
