package com.gustavohisan.apelieuser.repository.datasource.store

import com.gustavohisan.apelieuser.repository.model.store.StoreState

/**
 * Data source used in stores API requests.
 */
interface StoreDataSource {

    /**
     * Returns the store list to be shown on the home screen.
     *
     * @return the [StoreState] with the request state
     */
    suspend fun getMainScreenStoreList(): StoreState
}
