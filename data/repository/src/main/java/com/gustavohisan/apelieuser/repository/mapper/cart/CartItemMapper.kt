package com.gustavohisan.apelieuser.repository.mapper.cart

import com.gustavohisan.apelieuser.repository.mapper.store.ProductMapper
import com.gustavohisan.apelieuser.domain.model.cart.CartItem as DomainItem
import com.gustavohisan.apelieuser.repository.model.cart.CartItem as RepoItem

internal class CartItemMapper(private val productMapper: ProductMapper) {

    fun toRepo(repoCartItem: RepoItem): DomainItem =
        DomainItem(
            id = repoCartItem.id,
            product = productMapper.toDomain(repoCartItem.product),
            description = repoCartItem.description,
            quantity = repoCartItem.quantity
        )
}
