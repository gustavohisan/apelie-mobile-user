package com.gustavohisan.apelieuser.orders.mapper

import com.gustavohisan.apelieuser.domain.model.address.Address as DomainAddress
import com.gustavohisan.apelieuser.orders.model.Address as PresentationAddress

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
