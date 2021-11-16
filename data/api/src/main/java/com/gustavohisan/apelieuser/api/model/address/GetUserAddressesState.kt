package com.gustavohisan.apelieuser.api.model.address

internal sealed class GetUserAddressesState {

    data class Success(val addresses: List<Address>) : GetUserAddressesState()

    object Error : GetUserAddressesState()
}
