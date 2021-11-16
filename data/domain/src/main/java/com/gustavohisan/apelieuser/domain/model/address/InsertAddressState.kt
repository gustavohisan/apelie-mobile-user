package com.gustavohisan.apelieuser.domain.model.address

sealed class InsertAddressState {

    object Success: InsertAddressState()

    data class Error(val errors: List<InsertAddressErrorType>) : InsertAddressState()
}
