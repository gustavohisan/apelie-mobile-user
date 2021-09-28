package com.gustavohisan.apelieuser.store.mapper

import com.gustavohisan.apelieuser.store.model.ProductCategory
import com.gustavohisan.apelieuser.store.model.StoreItem


internal class StoreItemMapper {

    fun toPresentation(categories: List<ProductCategory>): List<StoreItem> {
        val storeItemList = mutableListOf<StoreItem>()
        categories.forEach { productCategory ->
            storeItemList.add(StoreItem.Category(productCategory.category))
            storeItemList.addAll(productCategory.productList.map { StoreItem.ProductItem(it) })
        }
        return storeItemList
    }
}
