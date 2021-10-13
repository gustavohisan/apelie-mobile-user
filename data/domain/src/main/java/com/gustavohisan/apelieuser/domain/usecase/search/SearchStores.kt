package com.gustavohisan.apelieuser.domain.usecase.search

import com.gustavohisan.apelieuser.domain.model.store.MainScreenStoreErrorType
import com.gustavohisan.apelieuser.domain.model.store.SearchStoreState
import com.gustavohisan.apelieuser.domain.repository.store.StoreRepository

class SearchStores(private val storeRepository: StoreRepository) {

    suspend operator fun invoke(query: String, categories: List<String>): SearchStoreState {
        return if (query.isBlank()) {
            SearchStoreState.EmptyQuery
        } else {
            val result =
                storeRepository.searchStores(query, categories.joinToString(",").ifBlank { null })
            if (result is SearchStoreState.Success) {
                checkIfResultIsEmpty(result)
            } else {
                result
            }
        }
    }

    private fun checkIfResultIsEmpty(result: SearchStoreState.Success): SearchStoreState =
        if (result.storeList.isNullOrEmpty()) {
            SearchStoreState.Error(MainScreenStoreErrorType.EMPTY_LIST)
        } else {
            result
        }
}
