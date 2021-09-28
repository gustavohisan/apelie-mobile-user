package com.gustavohisan.apelieuser.store.mapper

import com.gustavohisan.apelieuser.store.model.Owner as PresentationOwner
import com.gustavohisan.apelieuser.domain.model.store.Owner as DomainOwner

internal class OwnerMapper {

    fun toPresentation(domainOwner: DomainOwner): PresentationOwner =
        PresentationOwner(
            email = domainOwner.email,
            fullName = domainOwner.fullName,
            id = domainOwner.id,
            photoUrl = domainOwner.photoUrl
        )
}
