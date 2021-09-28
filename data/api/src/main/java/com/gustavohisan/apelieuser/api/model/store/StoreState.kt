package com.gustavohisan.apelieuser.api.model.store

internal sealed class StoreState {

    data class Success(val storeData: Store) : StoreState()

    object Error : StoreState()
}
