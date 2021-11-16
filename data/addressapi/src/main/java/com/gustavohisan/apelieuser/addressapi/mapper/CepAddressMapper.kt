package com.gustavohisan.apelieuser.addressapi.mapper

import com.gustavohisan.apelieuser.addressapi.model.CepAddress as ApiItem
import com.gustavohisan.apelieuser.repository.model.address.CepAddress as RepoItem

internal class CepAddressMapper {

    fun toRepo(apiItem: ApiItem): RepoItem =
        RepoItem(
            city = apiItem.city,
            district = apiItem.district,
            state = apiItem.state,
            street = apiItem.street
        )
}
