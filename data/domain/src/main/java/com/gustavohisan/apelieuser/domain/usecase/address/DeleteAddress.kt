package com.gustavohisan.apelieuser.domain.usecase.address

import com.gustavohisan.apelieuser.domain.repository.address.AddressRepository

class DeleteAddress(private val addressRepository: AddressRepository) {

    suspend operator fun invoke(addressId: Int): Boolean =
        addressRepository.deleteAddress(addressId)
}
