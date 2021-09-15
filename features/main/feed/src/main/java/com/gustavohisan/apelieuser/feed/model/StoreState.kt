package com.gustavohisan.apelieuser.feed.model

/**
 * Representation of the store state.
 */
internal sealed class StoreState {

    /**
     * Represents the store state when the list of stores was successfully retrieved.
     *
     * @param storeList the [List] of [Store]s
     */
    data class Success(val storeList: List<Store>) : StoreState()

    /**
     * Represents the store state when there was an server error retrieving the store list.
     */
    data class Error(val errorType: StoreErrorType) : StoreState()

    object Loading: StoreState()
}
