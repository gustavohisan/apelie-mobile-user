package com.gustavohisan.apelieuser.address.mapper

import com.gustavohisan.apelieuser.domain.model.address.GetAddressFromCepState as DomainState
import com.gustavohisan.apelieuser.address.model.GetAddressFromCepState as PresentationState

internal class GetAddressFromCepStateMapper(private val cepAddressMapper: CepAddressMapper) {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success(
                domainState.cepAddress?.let {
                    cepAddressMapper.toPresentation(
                        it
                    )
                }
            )
            is DomainState.Error -> PresentationState.Error
            is DomainState.Empty -> PresentationState.Empty
        }
}
