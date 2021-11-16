package com.gustavohisan.apelieuser.address.model

internal sealed class InsertAddressState {

    object Success: InsertAddressState()

    object Loading: InsertAddressState()

    data class Error(val errors: List<InsertAddressErrorType>) : InsertAddressState()

    object None: InsertAddressState()
}
