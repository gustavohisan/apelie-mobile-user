package com.gustavohisan.apelieuser.api.mapper.store

import com.gustavohisan.apelieuser.api.model.store.Owner as ApiOwner
import com.gustavohisan.apelieuser.repository.model.store.Owner as RepositoryOwner

internal class OwnerMapper {

    fun toRepo(apiOwner: ApiOwner): RepositoryOwner =
        RepositoryOwner(
            email = apiOwner.email,
            fullName = apiOwner.fullName,
            id = apiOwner.id,
            photoUrl = apiOwner.photoUrl ?: ""
        )
}
