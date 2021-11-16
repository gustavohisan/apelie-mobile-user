package com.gustavohisan.apelieuser.domain.model.address

sealed class GetUserAddressesState {

    data class Success(val addresses: List<Address>) : GetUserAddressesState()

    object Empty: GetUserAddressesState()

    object Error : GetUserAddressesState()
}
