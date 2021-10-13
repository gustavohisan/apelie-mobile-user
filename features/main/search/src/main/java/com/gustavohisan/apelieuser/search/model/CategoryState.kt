package com.gustavohisan.apelieuser.search.model

internal sealed class CategoryState {

    data class Success(val categories: List<Filter>) : CategoryState()

    data class Error(val errorType: CategoryErrorType) : CategoryState()

    object Loading : CategoryState()
}
