package com.gustavohisan.apelieuser.repository.mapper.store

import com.gustavohisan.apelieuser.domain.model.store.ProductCategory as DomainCategory
import com.gustavohisan.apelieuser.domain.model.store.Product as DomainProduct
import com.gustavohisan.apelieuser.repository.model.store.Product as RepositoryProduct

internal class ProductMapper {

    fun toDomain(repoProduct: List<RepositoryProduct>): List<DomainCategory> {
        val categories = repoProduct.map { product -> product.category }.distinct()
        return categories.map { category ->
            DomainCategory(
                category = category,
                productList = repoProduct
                    .filter { product -> product.category == category }
                    .map(::toDomain)
            )
        }
    }

    fun toDomain(repoProduct: RepositoryProduct): DomainProduct =
        DomainProduct(
            id = repoProduct.id,
            description = repoProduct.description,
            category = repoProduct.category,
            images = repoProduct.images,
            name = repoProduct.name,
            price = repoProduct.price,
            quantity = repoProduct.quantity
        )
}
