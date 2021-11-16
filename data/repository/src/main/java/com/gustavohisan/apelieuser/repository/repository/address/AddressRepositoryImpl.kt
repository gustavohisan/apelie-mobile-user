package com.gustavohisan.apelieuser.repository.repository.address

import com.gustavohisan.apelieuser.domain.model.address.GetAddressFromCepState
import com.gustavohisan.apelieuser.domain.model.address.GetUserAddressesState
import com.gustavohisan.apelieuser.domain.repository.address.AddressRepository
import com.gustavohisan.apelieuser.repository.datasource.address.AddressDataSource
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import com.gustavohisan.apelieuser.repository.mapper.address.GetUserAddressFromCepStateMapper
import com.gustavohisan.apelieuser.repository.mapper.address.GetUserAddressesStateMapper

internal class AddressRepositoryImpl(
    private val userApiDataSource: UserApiDataSource,
    private val addressDataSource: AddressDataSource,
    private val getUserAddressesStateMapper: GetUserAddressesStateMapper,
    private val getUserAddressFromCepStateMapper: GetUserAddressFromCepStateMapper
) : AddressRepository {

    override suspend fun insertAddress(
        city: String,
        complement: String,
        district: String,
        number: String,
        state: String,
        street: String,
        zipCode: String
    ): Boolean =
        userApiDataSource.insertAddress(city, complement, district, number, state, street, zipCode)

    override suspend fun deleteAddress(addressId: Int): Boolean =
        userApiDataSource.deleteAddress(addressId)

    override suspend fun getUserAddressList(): GetUserAddressesState =
        getUserAddressesStateMapper.toDomain(userApiDataSource.getUserAddresses())

    override suspend fun searchAddressByCep(cep: String): GetAddressFromCepState =
        getUserAddressFromCepStateMapper.toDomain(addressDataSource.getAddressFromCep(cep))

    override suspend fun updateAddress(
        addressId: Int,
        city: String,
        complement: String,
        district: String,
        number: String,
        state: String,
        street: String,
        zipCode: String
    ): Boolean = userApiDataSource.editAddress(
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
