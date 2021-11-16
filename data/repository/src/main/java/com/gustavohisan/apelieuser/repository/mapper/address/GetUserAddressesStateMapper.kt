package com.gustavohisan.apelieuser.repository.mapper.address

import com.gustavohisan.apelieuser.repository.model.address.GetUserAddressesState as RepoState
import com.gustavohisan.apelieuser.domain.model.address.GetUserAddressesState as DomainState

internal class GetUserAddressesStateMapper(private val addressMapper: AddressMapper) {

    fun toDomain(repoState: RepoState): DomainState =
        when (repoState) {
            is RepoState.Success -> DomainState.Success(repoState.addresses.map {
                addressMapper.toDomain(
                    it
                )
            })
            is RepoState.Error -> DomainState.Error
        }
}
