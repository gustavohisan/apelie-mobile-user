package com.gustavohisan.apelieuser.api.mapper.cart

import com.gustavohisan.apelieuser.api.mapper.store.ProductMapper
import com.gustavohisan.apelieuser.api.model.cart.CartItem as ApiItem
import com.gustavohisan.apelieuser.repository.model.cart.CartItem as RepoItem

internal class CartItemMapper(private val productMapper: ProductMapper) {

    fun toRepo(apiCartItem: ApiItem): RepoItem =
        RepoItem(
            id = apiCartItem.id,
            product = productMapper.toRepo(apiCartItem.product),
            description = apiCartItem.description,
            quantity = apiCartItem.quantity
        )
}
