package com.gustavohisan.apelieuser.repository.model.store

sealed class CategoryState {

    data class Success(val categories: List<String>) : CategoryState()

    object Error : CategoryState()
}
