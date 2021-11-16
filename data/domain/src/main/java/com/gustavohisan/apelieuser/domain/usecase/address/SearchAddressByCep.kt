package com.gustavohisan.apelieuser.domain.usecase.address

import com.gustavohisan.apelieuser.domain.model.address.CepAddress
import com.gustavohisan.apelieuser.domain.model.address.GetAddressFromCepState
import com.gustavohisan.apelieuser.domain.repository.address.AddressRepository

class SearchAddressByCep(private val addressRepository: AddressRepository) {

    suspend operator fun invoke(cep: String) : GetAddressFromCepState {
        val cepNumbers = cep.filter { it.isDigit() }
        return if (cepNumbers.length == 8) {
            val state = addressRepository.searchAddressByCep(cepNumbers)
            if (state is GetAddressFromCepState.Success) {
                checkIfAddressesAreEmpty(state.cepAddress)
            } else {
                state
            }
        } else {
            GetAddressFromCepState.Error
        }
    }

    private fun checkIfAddressesAreEmpty(address: CepAddress?) =
        if (address == null) {
            GetAddressFromCepState.Empty
        } else {
            GetAddressFromCepState.Success(address)
        }
}
