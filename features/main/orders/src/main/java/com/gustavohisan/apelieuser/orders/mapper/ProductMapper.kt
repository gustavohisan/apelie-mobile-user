package com.gustavohisan.apelieuser.orders.mapper

import com.gustavohisan.apelieuser.orders.model.Product as PresentationProduct
import com.gustavohisan.apelieuser.domain.model.store.Product as DomainProduct

internal class ProductMapper {

    fun toPresentation(domainProduct: DomainProduct): PresentationProduct =
        PresentationProduct(
            id = domainProduct.id,
            description = domainProduct.description,
            category = domainProduct.category,
            images = domainProduct.images,
            name = domainProduct.name,
            price = domainProduct.price,
            quantity = domainProduct.quantity
        )
}
