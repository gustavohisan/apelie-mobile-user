package com.gustavohisan.apelieuser.repository.datasource.address

import com.gustavohisan.apelieuser.repository.model.address.GetAddressFromCepState

interface AddressDataSource {

    suspend fun getAddressFromCep(cep: String): GetAddressFromCepState
}
