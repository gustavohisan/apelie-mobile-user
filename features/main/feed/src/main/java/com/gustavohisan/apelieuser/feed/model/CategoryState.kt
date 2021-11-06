package com.gustavohisan.apelieuser.feed.model

internal sealed class CategoryState {

    data class Success(val categories: List<String>) : CategoryState()

    data class Error(val errorType: CategoryErrorType) : CategoryState()

    object Loading : CategoryState()
}
