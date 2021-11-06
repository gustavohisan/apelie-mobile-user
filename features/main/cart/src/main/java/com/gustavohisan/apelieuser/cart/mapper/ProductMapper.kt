package com.gustavohisan.apelieuser.cart.mapper

import com.gustavohisan.apelieuser.domain.model.store.Product as DomainProduct
import com.gustavohisan.apelieuser.cart.model.Product as PresentationProduct

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
