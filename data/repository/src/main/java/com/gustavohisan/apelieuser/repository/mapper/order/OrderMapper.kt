package com.gustavohisan.apelieuser.repository.mapper.order

import com.gustavohisan.apelieuser.repository.mapper.address.AddressMapper
import com.gustavohisan.apelieuser.repository.mapper.store.StoreMapper
import com.gustavohisan.apelieuser.domain.model.order.Order as DomainItem
import com.gustavohisan.apelieuser.repository.model.order.Order as RepoItem

internal class OrderMapper(
    private val storeMapper: StoreMapper,
    private val itemListMapper: ItemListMapper,
    private val addressMapper: AddressMapper
) {

    fun toDomain(repoItem: RepoItem): DomainItem =
        DomainItem(
            orderId = repoItem.orderId,
            trackingCode = repoItem.trackingCode,
            status = repoItem.status,
            paymentMethod = repoItem.paymentMethod,
            createdAt = repoItem.createdAt,
            totalValue = repoItem.totalValue,
            store = storeMapper.toDomain(repoItem.store),
            itemList = repoItem.itemList.map { itemListMapper.toDomain(it) },
            address = addressMapper.toDomain(repoItem.address),
            hasReview = repoItem.hasReview
        )
}
