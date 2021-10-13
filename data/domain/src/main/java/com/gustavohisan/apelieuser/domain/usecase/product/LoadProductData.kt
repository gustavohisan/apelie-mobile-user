package com.gustavohisan.apelieuser.domain.usecase.product

import com.gustavohisan.apelieuser.domain.model.store.ProductState
import com.gustavohisan.apelieuser.domain.repository.store.StoreRepository

class LoadProductData(private val storeRepository: StoreRepository) {

    suspend operator fun invoke(productId: Int): ProductState =
        storeRepository.getProductData(productId)
}
