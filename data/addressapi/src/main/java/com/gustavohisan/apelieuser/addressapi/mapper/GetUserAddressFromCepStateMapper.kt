package com.gustavohisan.apelieuser.addressapi.mapper

import com.gustavohisan.apelieuser.addressapi.model.GetAddressFromCepState as ApiState
import com.gustavohisan.apelieuser.repository.model.address.GetAddressFromCepState as RepoState

internal class GetUserAddressFromCepStateMapper(private val cepAddressMapper: CepAddressMapper) {

    fun toRepo(apiState: ApiState): RepoState =
        when (apiState) {
            is ApiState.Success -> RepoState.Success(apiState.cepAddress?.let {
                cepAddressMapper.toRepo(
                    it
                )
            })
            is ApiState.Error -> RepoState.Error
        }
}
