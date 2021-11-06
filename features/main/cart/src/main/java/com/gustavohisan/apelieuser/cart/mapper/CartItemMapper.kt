package com.gustavohisan.apelieuser.cart.mapper

import com.gustavohisan.apelieuser.domain.model.cart.CartItem as DomainItem
import com.gustavohisan.apelieuser.cart.model.CartItem as PresentationItem

internal class CartItemMapper(private val productMapper: ProductMapper) {

    fun toPresentation(domainItem: DomainItem) : PresentationItem =
        PresentationItem(
            id = domainItem.id,
            product = productMapper.toPresentation(domainItem.product),
            description = domainItem.description,
            quantity = domainItem.quantity
        )
}
