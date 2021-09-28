package com.gustavohisan.apelieuser.store.mapper

import com.gustavohisan.apelieuser.store.model.Product as PresentationProduct
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
