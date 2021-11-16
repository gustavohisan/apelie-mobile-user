package com.gustavohisan.apelieuser.checkout.mapper

import com.gustavohisan.apelieuser.domain.model.address.Address as DomainAddress
import com.gustavohisan.apelieuser.checkout.model.Address as PresentationAddress

internal class AddressMapper {

    fun toPresentation(domainAddress: DomainAddress): PresentationAddress =
        PresentationAddress(
            addressId = domainAddress.addressId,
            city = domainAddress.city,
            complement = domainAddress.complement,
            district = domainAddress.district,
            number = domainAddress.number,
            state = domainAddress.state,
            street = domainAddress.street,
            zipCode = domainAddress.zipCode
        )
}
