package com.gustavohisan.apelieuser.api.mapper.order

import com.gustavohisan.apelieuser.api.mapper.address.AddressMapper
import com.gustavohisan.apelieuser.api.mapper.store.StoreMapper
import com.gustavohisan.apelieuser.api.model.orders.Order as ApiItem
import com.gustavohisan.apelieuser.repository.model.order.Order as RepoItem

internal class OrderMapper(
    private val storeMapper: StoreMapper,
    private val itemListMapper: ItemListMapper,
    private val addressMapper: AddressMapper
) {

    fun toRepo(apiItem: ApiItem): RepoItem =
        RepoItem(
            orderId = apiItem.orderId,
            trackingCode = apiItem.trackingCode ?: "",
            status = apiItem.status,
            paymentMethod = apiItem.paymentMethod,
            createdAt = apiItem.createdAt,
            totalValue = apiItem.totalValue,
            store = storeMapper.toRepo(apiItem.store),
            itemList = apiItem.itemList?.map { itemListMapper.toRepo(it) } ?: listOf(),
            address = addressMapper.toRepo(apiItem.address),
            hasReview = apiItem.review != null
        )
}
