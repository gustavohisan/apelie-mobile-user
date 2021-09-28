package com.gustavohisan.apelieuser.store.model

internal sealed class StoreItem {

    data class Category(val name: String) : StoreItem()

    data class ProductItem(val product: Product) : StoreItem()
}
