package com.gustavohisan.apelieuser.address.mapper

import com.gustavohisan.apelieuser.address.model.InsertAddressState as PresentationState
import com.gustavohisan.apelieuser.domain.model.address.InsertAddressState as DomainState

internal class InsertAddressStateMapper(private val errorTypeMapper: InsertAddressStateErrorTypeMapper) {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success
            is DomainState.Error -> PresentationState.Error(domainState.errors.map { error ->
                errorTypeMapper.toPresentation(
                    error
                )
            })
        }
}
