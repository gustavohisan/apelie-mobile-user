package com.gustavohisan.apelieuser.domain.usecase.store

import com.gustavohisan.apelieuser.domain.model.store.StoreState
import com.gustavohisan.apelieuser.domain.repository.store.StoreRepository

class LoadStoreData(private val storeRepository: StoreRepository) {

    suspend operator fun invoke(storeId: Int): StoreState = storeRepository.getStoreData(storeId)
}
