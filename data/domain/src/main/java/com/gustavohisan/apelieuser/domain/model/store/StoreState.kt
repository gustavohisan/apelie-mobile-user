package com.gustavohisan.apelieuser.domain.model.store

/**
 * Representation of the store state.
 */
sealed class StoreState {

    data class Success(val store: Store) : StoreState()

    data class Error(val errorType: StoreErrorType) : StoreState()
}
