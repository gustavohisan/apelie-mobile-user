package com.gustavohisan.apelieuser.domain.model.store

/**
 * Representation of the store state.
 */
sealed class SearchStoreState {

    /**
     * Represents the store state when the list of stores was successfully retrieved.
     *
     * @param storeList the [List] of [Store]s
     */
    data class Success(val storeList: List<MainScreenStore>?) : SearchStoreState()

    /**
     * Represents the store state when there was an server error retrieving the store list.
     */
    data class Error(val errorType: MainScreenStoreErrorType) : SearchStoreState()

    object EmptyQuery : SearchStoreState()
}
