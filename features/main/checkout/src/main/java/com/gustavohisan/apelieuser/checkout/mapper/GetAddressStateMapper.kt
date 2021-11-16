package com.gustavohisan.apelieuser.checkout.mapper

import com.gustavohisan.apelieuser.domain.model.address.GetUserAddressesState as DomainState
import com.gustavohisan.apelieuser.checkout.model.GetUserAddressesState as PresentationState

internal class GetAddressStateMapper(private val addressMapper: AddressMapper) {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success(domainState.addresses.map {
                addressMapper.toPresentation(
                    it
                )
            })
            is DomainState.Empty -> PresentationState.Empty
            is DomainState.Error -> PresentationState.Error
        }
}
