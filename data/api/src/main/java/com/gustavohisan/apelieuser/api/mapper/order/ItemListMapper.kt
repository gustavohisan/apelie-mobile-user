package com.gustavohisan.apelieuser.api.mapper.order

import com.gustavohisan.apelieuser.api.mapper.store.ProductMapper
import com.gustavohisan.apelieuser.api.model.orders.ItemList as ApiItem
import com.gustavohisan.apelieuser.repository.model.order.ItemList as RepoItem

internal class ItemListMapper(private val productMapper: ProductMapper) {

    fun toRepo(apiItem: ApiItem): RepoItem =
        RepoItem(
            product = productMapper.toRepo(apiItem.product),
            description = apiItem.description,
            quantity = apiItem.quantity
        )
}
