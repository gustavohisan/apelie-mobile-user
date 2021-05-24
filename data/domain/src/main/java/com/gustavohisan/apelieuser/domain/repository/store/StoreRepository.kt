package com.gustavohisan.apelieuser.domain.repository.store

import com.gustavohisan.apelieuser.domain.model.store.StoreState

/**
 * Repository used for the stores requests.
 */
interface StoreRepository {

    /**
     * Returns the list of stores to be displayed on the home screen.
     *
     * @return the [StoreState] with the state of the request
     */
    suspend fun getMainScreenStores(): StoreState
}
