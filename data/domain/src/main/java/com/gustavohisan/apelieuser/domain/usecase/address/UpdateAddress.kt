package com.gustavohisan.apelieuser.domain.usecase.address

import com.gustavohisan.apelieuser.domain.repository.address.AddressRepository

class UpdateAddress(private val addressRepository: AddressRepository) {

    suspend operator fun invoke(
        addressId: Int,
        city: String,
        complement: String,
        district: String,
        number: String,
        state: String,
        street: String,
        zipCode: String
    ): Boolean =
        addressRepository.updateAddress(
            addressId,
            city,
            complement,
            district,
            number,
            state,
            street,
            zipCode
        )
}
