package com.gustavohisan.apelieuser.api.mapper.address

import com.gustavohisan.apelieuser.api.model.address.GetUserAddressesState as ApiState
import com.gustavohisan.apelieuser.repository.model.address.GetUserAddressesState as RepoState

internal class GetUserAddressesStateMapper(private val addressMapper: AddressMapper) {

    fun toRepo(apiState: ApiState): RepoState =
        when (apiState) {
            is ApiState.Success -> RepoState.Success(apiState.addresses.map {
                addressMapper.toRepo(
                    it
                )
            })
            is ApiState.Error -> RepoState.Error
        }
}
