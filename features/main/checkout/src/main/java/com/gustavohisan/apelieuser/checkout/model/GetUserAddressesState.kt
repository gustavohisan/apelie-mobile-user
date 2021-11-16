package com.gustavohisan.apelieuser.checkout.model

internal sealed class GetUserAddressesState {

    data class Success(val addresses: List<Address>) : GetUserAddressesState()

    object Empty: GetUserAddressesState()

    object Error : GetUserAddressesState()

    object Loading : GetUserAddressesState()
}
