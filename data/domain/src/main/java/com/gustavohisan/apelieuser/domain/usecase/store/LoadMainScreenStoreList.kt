package com.gustavohisan.apelieuser.domain.usecase.store

import com.gustavohisan.apelieuser.domain.model.store.StoreErrorType
import com.gustavohisan.apelieuser.domain.model.store.StoreState
import com.gustavohisan.apelieuser.domain.repository.store.StoreRepository

/**
 * Use-case used to load the list of stores to be displayed on the home screen.
 *
 * @param storeRepository repository used to retrieve the store list
 */
class LoadMainScreenStoreList(private val storeRepository: StoreRepository) {

    /**
     * Retrieves the list of stores.
     *
     * @return a [StoreState] with the state of the request
     */
    suspend operator fun invoke(): StoreState {
        val result = storeRepository.getMainScreenStores()
        return when (result) {
            is StoreState.Success -> checkIfResultIsEmpty(result)
            is StoreState.Error -> result
        }
    }

    private fun checkIfResultIsEmpty(result: StoreState.Success): StoreState =
        if (result.storeList.isNullOrEmpty()) {
            StoreState.Error(StoreErrorType.EMPTY_LIST)
        } else {
            result
        }
}
