package com.gustavohisan.apelieuser.address.mapper

import com.gustavohisan.apelieuser.domain.model.address.InsertAddressErrorType as DomainErrorType
import com.gustavohisan.apelieuser.address.model.InsertAddressErrorType as PresentationErrorType

internal class InsertAddressStateErrorTypeMapper {

    fun toPresentation(domainStoreErrorType: DomainErrorType): PresentationErrorType =
        when (domainStoreErrorType) {
            DomainErrorType.SERVER_UNAVAILABLE -> PresentationErrorType.SERVER_UNAVAILABLE
            DomainErrorType.INVALID_CITY -> PresentationErrorType.INVALID_CITY
            DomainErrorType.INVALID_DISTRICT -> PresentationErrorType.INVALID_DISTRICT
            DomainErrorType.INVALID_NUMBER -> PresentationErrorType.INVALID_NUMBER
            DomainErrorType.INVALID_STATE -> PresentationErrorType.INVALID_STATE
            DomainErrorType.INVALID_STREET -> PresentationErrorType.INVALID_STREET
            DomainErrorType.INVALID_ZIP_CODE -> PresentationErrorType.INVALID_ZIP_CODE
        }
}
