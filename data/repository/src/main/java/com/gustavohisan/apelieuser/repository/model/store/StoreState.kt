package com.gustavohisan.apelieuser.repository.model.store

/**
 * Representation of the store state.
 */
sealed class StoreState {

    data class Success(val store: Store) : StoreState()

    object Error : StoreState()
}
