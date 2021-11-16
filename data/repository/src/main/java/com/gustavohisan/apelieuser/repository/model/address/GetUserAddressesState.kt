package com.gustavohisan.apelieuser.repository.model.address

sealed class GetUserAddressesState {

    data class Success(val addresses: List<Address>) : GetUserAddressesState()

    object Error : GetUserAddressesState()
}
