package com.gustavohisan.apelieuser.api.mapper.store

import com.gustavohisan.apelieuser.api.model.product.Product as ApiProduct
import com.gustavohisan.apelieuser.repository.model.store.Product as RepositoryProduct

internal class ProductMapper {

    fun toRepo(apiProduct: ApiProduct): RepositoryProduct =
        RepositoryProduct(
            id = apiProduct.id,
            description = apiProduct.description,
            category = apiProduct.category,
            images = apiProduct.images.map { productImage -> productImage.url },
            name = apiProduct.name,
            price = apiProduct.price,
            quantity = apiProduct.quantity
        )
}
