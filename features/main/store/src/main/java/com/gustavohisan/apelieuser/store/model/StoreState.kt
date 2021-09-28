package com.gustavohisan.apelieuser.store.model

/**
 * Representation of the store state.
 */
internal sealed class StoreState {

    data class Success(val store: Store) : StoreState()

    data class Error(val errorType: StoreErrorType) : StoreState()

    object Loading : StoreState()
}
