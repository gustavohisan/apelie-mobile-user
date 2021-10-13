package com.gustavohisan.apelieuser.product.mapper

import com.gustavohisan.apelieuser.domain.model.store.ProductErrorType as DomainErrorType
import com.gustavohisan.apelieuser.product.model.ProductErrorType as PresentationErrorType

internal class ProductErrorTypeMapper {

    fun toPresentation(domainErrorType: DomainErrorType): PresentationErrorType =
        when (domainErrorType) {
            DomainErrorType.SERVER_UNAVAILABLE -> PresentationErrorType.SERVER_UNAVAILABLE
        }
}
