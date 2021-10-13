package com.gustavohisan.apelieuser.search.model

/**
 * Representation of the store state.
 */
internal sealed class SearchStoresState {

    /**
     * Represents the store state when the list of stores was successfully retrieved.
     *
     * @param searchStoresList the [List] of [SearchStore]s
     */
    data class Success(val searchStoresList: List<SearchStore>) : SearchStoresState()

    /**
     * Represents the store state when there was an server error retrieving the store list.
     */
    data class Error(val errorType: SearchStoreErrorType) : SearchStoresState()

    object Loading: SearchStoresState()

    object NoQuery: SearchStoresState()
}
