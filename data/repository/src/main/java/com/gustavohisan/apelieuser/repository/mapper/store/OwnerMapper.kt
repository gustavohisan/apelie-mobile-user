package com.gustavohisan.apelieuser.repository.mapper.store

import com.gustavohisan.apelieuser.domain.model.store.Owner as DomainOwner
import com.gustavohisan.apelieuser.repository.model.store.Owner as RepositoryOwner

internal class OwnerMapper {

    fun toDomain(repositoryOwner: RepositoryOwner): DomainOwner =
        DomainOwner(
            email = repositoryOwner.email,
            fullName = repositoryOwner.fullName,
            id = repositoryOwner.id,
            photoUrl = repositoryOwner.photoUrl
        )
}
