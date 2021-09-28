package com.gustavohisan.apelieuser.domain.usecase.store

import com.gustavohisan.apelieuser.domain.model.store.MainScreenStoreErrorType
import com.gustavohisan.apelieuser.domain.model.store.MainScreenStoreState
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
     * @return a [MainScreenStoreState] with the state of the request
     */
    suspend operator fun invoke(): MainScreenStoreState {
        val result = storeRepository.getMainScreenStores()
        return when (result) {
            is MainScreenStoreState.Success -> checkIfResultIsEmpty(result)
            is MainScreenStoreState.Error -> result
        }
    }

    private fun checkIfResultIsEmpty(result: MainScreenStoreState.Success): MainScreenStoreState =
        if (result.storeList.isNullOrEmpty()) {
            MainScreenStoreState.Error(MainScreenStoreErrorType.EMPTY_LIST)
        } else {
            result
        }
}
