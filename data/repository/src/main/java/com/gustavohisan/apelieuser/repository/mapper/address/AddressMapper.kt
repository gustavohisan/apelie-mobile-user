package com.gustavohisan.apelieuser.repository.mapper.address

import com.gustavohisan.apelieuser.domain.model.address.Address as DomainItem
import com.gustavohisan.apelieuser.repository.model.address.Address as RepoItem

internal class AddressMapper {

    fun toDomain(repoItem: RepoItem): DomainItem =
        DomainItem(
            addressId = repoItem.addressId,
            city = repoItem.city,
            complement = repoItem.complement,
            district = repoItem.district,
            number = repoItem.number,
            state = repoItem.state,
            street = repoItem.street,
            zipCode = repoItem.zipCode
        )
}
