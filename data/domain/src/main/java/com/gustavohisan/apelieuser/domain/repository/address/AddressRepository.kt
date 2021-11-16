package com.gustavohisan.apelieuser.domain.repository.address

import com.gustavohisan.apelieuser.domain.model.address.GetAddressFromCepState
import com.gustavohisan.apelieuser.domain.model.address.GetUserAddressesState

interface AddressRepository {

    suspend fun insertAddress(
        city: String,
        complement: String,
        district: String,
        number: String,
        state: String,
        street: String,
        zipCode: String
    ): Boolean

    suspend fun deleteAddress(addressId: Int): Boolean

    suspend fun getUserAddressList(): GetUserAddressesState

    suspend fun searchAddressByCep(cep: String): GetAddressFromCepState

    suspend fun updateAddress(
        addressId: Int,
        city: String,
        complement: String,
        district: String,
        number: String,
        state: String,
        street: String,
        zipCode: String
    ): Boolean
}
