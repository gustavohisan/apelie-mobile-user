package com.gustavohisan.apelieuser.domain.model.store

sealed class ProductState {

    data class Success(val product: Product) : ProductState()

    data class Error(val errorType: ProductErrorType) : ProductState()
}
