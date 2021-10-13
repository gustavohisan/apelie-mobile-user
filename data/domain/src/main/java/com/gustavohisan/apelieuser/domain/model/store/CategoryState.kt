package com.gustavohisan.apelieuser.domain.model.store

sealed class CategoryState {

    data class Success(val categories: List<String>) : CategoryState()

    data class Error(val errorType: CategoryErrorType) : CategoryState()
}
