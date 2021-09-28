package com.gustavohisan.apelieuser.store.mapper

import com.gustavohisan.apelieuser.domain.model.store.StoreErrorType as DomainStoreErrorType
import com.gustavohisan.apelieuser.store.model.StoreErrorType as PresentationStoreErrorType

internal class StoreErrorTypeMapper {

    fun toPresentation(domainStoreErrorType: DomainStoreErrorType): PresentationStoreErrorType =
        when (domainStoreErrorType) {
            DomainStoreErrorType.SERVER_UNAVAILABLE -> PresentationStoreErrorType.SERVER_UNAVAILABLE
        }
}
