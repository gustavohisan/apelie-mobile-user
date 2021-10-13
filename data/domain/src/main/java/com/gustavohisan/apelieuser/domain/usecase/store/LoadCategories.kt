package com.gustavohisan.apelieuser.domain.usecase.store

import com.gustavohisan.apelieuser.domain.model.store.CategoryState
import com.gustavohisan.apelieuser.domain.repository.store.StoreRepository

class LoadCategories(private val storeRepository: StoreRepository) {

    suspend operator fun invoke(): CategoryState = storeRepository.getCategories()
}
