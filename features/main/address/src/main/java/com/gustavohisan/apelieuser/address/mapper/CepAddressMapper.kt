package com.gustavohisan.apelieuser.address.mapper

import com.gustavohisan.apelieuser.domain.model.address.CepAddress as DomainAddress
import com.gustavohisan.apelieuser.address.model.CepAddress as PresentationAddress

internal class CepAddressMapper {

    fun toPresentation(domainAddress: DomainAddress): PresentationAddress =
        PresentationAddress(
            city = domainAddress.city,
            district = domainAddress.district,
            state = domainAddress.state,
            street = domainAddress.street
        )
}
