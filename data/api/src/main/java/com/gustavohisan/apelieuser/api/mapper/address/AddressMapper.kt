package com.gustavohisan.apelieuser.api.mapper.address

import com.gustavohisan.apelieuser.api.model.address.Address as ApiItem
import com.gustavohisan.apelieuser.repository.model.address.Address as RepoItem

internal class AddressMapper {

    fun toRepo(apiItem: ApiItem): RepoItem =
        RepoItem(
            addressId = apiItem.addressId,
            city = apiItem.city,
            complement = apiItem.complement,
            district = apiItem.district,
            number = apiItem.number,
            state = apiItem.state,
            street = apiItem.street,
            zipCode = apiItem.zipCode
        )
}
