package com.gustavohisan.apelieuser.addressapi.datasource

import com.gustavohisan.apelieuser.addressapi.endpoints.AddressEndpoints
import com.gustavohisan.apelieuser.addressapi.factory.ApiFactory
import com.gustavohisan.apelieuser.addressapi.mapper.GetUserAddressFromCepStateMapper
import com.gustavohisan.apelieuser.addressapi.model.GetAddressFromCepState
import com.gustavohisan.apelieuser.repository.datasource.address.AddressDataSource
import com.gustavohisan.apelieuser.repository.model.address.GetAddressFromCepState as RepoState

internal class AddressDataSourceImpl(
    apiFactory: ApiFactory,
    private val GetUserAddressFromCepStateMapper: GetUserAddressFromCepStateMapper
) : AddressDataSource {

    private val endpoint = apiFactory.getRetrofitInstance().create(AddressEndpoints::class.java)

    override suspend fun getAddressFromCep(cep: String): RepoState {
        val callback = endpoint.getAddressByCep(cep)
        return GetUserAddressFromCepStateMapper.toRepo(
            if (callback.code() == 200) {
                GetAddressFromCepState.Success(callback.body())
            } else {
                GetAddressFromCepState.Error
            }
        )
    }
}
