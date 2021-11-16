package com.gustavohisan.apelieuser.repository.mapper.address

import com.gustavohisan.apelieuser.domain.model.address.GetAddressFromCepState as DomainState
import com.gustavohisan.apelieuser.repository.model.address.GetAddressFromCepState as RepoState

internal class GetUserAddressFromCepStateMapper(private val cepAddressMapper: CepAddressMapper) {

    fun toDomain(repoState: RepoState): DomainState =
        when (repoState) {
            is RepoState.Success -> DomainState.Success(repoState.cepAddress?.let {
                cepAddressMapper.toDomain(
                    it
                )
            })
            is RepoState.Error -> DomainState.Error
        }
}
