package com.gustavohisan.apelieuser.feed.model

/**
 * Representation of the store state.
 */
internal sealed class MainScreenStoreState {

    /**
     * Represents the store state when the list of stores was successfully retrieved.
     *
     * @param mainScreenStoreList the [List] of [MainScreenStore]s
     */
    data class Success(val mainScreenStoreList: List<MainScreenStore>) : MainScreenStoreState()

    /**
     * Represents the store state when there was an server error retrieving the store list.
     */
    data class Error(val errorType: MainScreenStoreErrorType) : MainScreenStoreState()

    object Loading: MainScreenStoreState()
}
