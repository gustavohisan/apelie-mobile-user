package com.gustavohisan.apelieuser.repository.model.store

sealed class ProductState {

    data class Success(val product: Product) : ProductState()

    object Error : ProductState()
}
