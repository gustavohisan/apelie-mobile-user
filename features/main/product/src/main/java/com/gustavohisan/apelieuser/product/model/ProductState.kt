package com.gustavohisan.apelieuser.product.model

internal sealed class ProductState {

    data class Success(val product: Product) : ProductState()

    data class Error(val errorType: ProductErrorType) : ProductState()

    object Loading : ProductState()
}
