package com.gustavohisan.apelieuser.orders.mapper

import com.gustavohisan.apelieuser.orders.model.ProductCategory as PresentationCategory
import com.gustavohisan.apelieuser.domain.model.store.ProductCategory as DomainCategory

internal class ProductCategoryMapper(private val productMapper: ProductMapper) {

    fun toPresentation(domainCategory: DomainCategory): PresentationCategory =
        PresentationCategory(
            category = domainCategory.category,
            productList = domainCategory.productList.map { product ->
                productMapper.toPresentation(
                    product
                )
            }
        )
}
