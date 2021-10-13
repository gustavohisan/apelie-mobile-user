package com.gustavohisan.apelieuser.api.model.product

internal sealed class ProductState {

    data class Success(val product: Product) : ProductState()

    object Error : ProductState()
}
