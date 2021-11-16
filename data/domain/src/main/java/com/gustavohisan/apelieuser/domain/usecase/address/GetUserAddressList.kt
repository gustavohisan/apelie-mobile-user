package com.gustavohisan.apelieuser.domain.usecase.address

import com.gustavohisan.apelieuser.domain.model.address.Address
import com.gustavohisan.apelieuser.domain.model.address.GetUserAddressesState
import com.gustavohisan.apelieuser.domain.repository.address.AddressRepository

class GetUserAddressList(private val addressRepository: AddressRepository) {

    suspend operator fun invoke(): GetUserAddressesState {
        val state = addressRepository.getUserAddressList()
        return if (state is GetUserAddressesState.Success) {
            checkIfAddressesAreEmpty(state.addresses)
        } else {
            state
        }
    }

    private fun checkIfAddressesAreEmpty(addresses: List<Address>) =
        if (addresses.isEmpty()) {
            GetUserAddressesState.Empty
        } else {
            GetUserAddressesState.Success(addresses)
        }
}
