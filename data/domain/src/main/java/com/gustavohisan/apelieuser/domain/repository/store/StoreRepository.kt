package com.gustavohisan.apelieuser.domain.repository.store

import com.gustavohisan.apelieuser.domain.model.store.MainScreenStoreState
import com.gustavohisan.apelieuser.domain.model.store.StoreState

/**
 * Repository used for the stores requests.
 */
interface StoreRepository {

    /**
     * Returns the list of stores to be displayed on the home screen.
     *
     * @return the [MainScreenStoreState] with the state of the request
     */
    suspend fun getMainScreenStores(): MainScreenStoreState

    suspend fun getStoreData(storeId: Int): StoreState
}
