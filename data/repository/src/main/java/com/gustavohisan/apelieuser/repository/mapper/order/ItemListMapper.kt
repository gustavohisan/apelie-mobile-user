package com.gustavohisan.apelieuser.repository.mapper.order

import com.gustavohisan.apelieuser.repository.mapper.store.ProductMapper
import com.gustavohisan.apelieuser.domain.model.order.ItemList as DomainItem
import com.gustavohisan.apelieuser.repository.model.order.ItemList as RepoItem

internal class ItemListMapper(private val productMapper: ProductMapper) {

    fun toDomain(repoItem: RepoItem): DomainItem =
        DomainItem(
            product = productMapper.toDomain(repoItem.product),
            description = repoItem.description,
            quantity = repoItem.quantity
        )
}
