package com.gustavohisan.apelieuser.orders.mapper

import com.gustavohisan.apelieuser.orders.model.ItemList as PresentationItem
import com.gustavohisan.apelieuser.domain.model.order.ItemList as DomainItem

internal class ItemListMapper(private val productMapper: ProductMapper) {

    fun toPresentation(domainItem: DomainItem): PresentationItem =
        PresentationItem(
            product = productMapper.toPresentation(domainItem.product),
            description = domainItem.description,
            quantity = domainItem.quantity
        )
}
