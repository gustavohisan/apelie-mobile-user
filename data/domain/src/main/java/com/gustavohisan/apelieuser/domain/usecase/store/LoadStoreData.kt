package com.gustavohisan.apelieuser.domain.usecase.store

import com.gustavohisan.apelieuser.domain.model.store.StoreState
import com.gustavohisan.apelieuser.domain.repository.store.StoreRepository

class LoadStoreData(private val storeRepository: StoreRepository) {

    suspend operator fun invoke(storeId: Int): StoreState {
        val state = storeRepository.getStoreData(storeId)
        return if (state is StoreState.Success) {
            val products =
                state.store.products.sortedBy { productCategory -> productCategory.category }
                    .map { productCategory ->
                        productCategory
                            .copy(productList = productCategory.productList
                                .sortedBy { product -> product.name })
                    }
            StoreState.Success(state.store.copy(products = products))
        } else {
            state
        }
    }
}
