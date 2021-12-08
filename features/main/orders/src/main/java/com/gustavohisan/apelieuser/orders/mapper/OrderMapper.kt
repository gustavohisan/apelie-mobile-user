package com.gustavohisan.apelieuser.orders.mapper

import com.gustavohisan.apelieuser.orders.model.Order as PresentationItem
import com.gustavohisan.apelieuser.domain.model.order.Order as DomainItem

internal class OrderMapper(
    private val storeMapper: StoreMapper,
    private val itemListMapper: ItemListMapper,
    private val addressMapper: AddressMapper
) {

    fun toPresentation(domainItem: DomainItem): PresentationItem =
        PresentationItem(
            orderId = domainItem.orderId,
            trackingCode = domainItem.trackingCode,
            status = domainItem.status,
            paymentMethod = domainItem.paymentMethod,
            createdAt = domainItem.createdAt,
            totalValue = domainItem.totalValue,
            store = storeMapper.toPresentation(domainItem.store),
            itemList = domainItem.itemList.map { itemListMapper.toPresentation(it) },
            address = addressMapper.toPresentation(domainItem.address),
            hasReview = domainItem.hasReview
        )
}
