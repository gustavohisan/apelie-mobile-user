package com.gustavohisan.apelieuser.repository.mapper.address

import com.gustavohisan.apelieuser.domain.model.address.CepAddress as DomainItem
import com.gustavohisan.apelieuser.repository.model.address.CepAddress as RepoItem

internal class CepAddressMapper {

    fun toDomain(repoItem: RepoItem): DomainItem =
        DomainItem(
            city = repoItem.city,
            district = repoItem.district,
            state = repoItem.state,
            street = repoItem.street
        )
}
