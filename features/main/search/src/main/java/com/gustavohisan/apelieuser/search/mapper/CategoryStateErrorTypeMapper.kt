package com.gustavohisan.apelieuser.search.mapper

import com.gustavohisan.apelieuser.domain.model.store.CategoryErrorType as DomainErrorType
import com.gustavohisan.apelieuser.search.model.CategoryErrorType as PresentationErrorType

internal class CategoryStateErrorTypeMapper {

    fun toPresentation(domainErrorType: DomainErrorType): PresentationErrorType =
        when (domainErrorType) {
            DomainErrorType.SERVER_UNAVAILABLE -> PresentationErrorType.SERVER_UNAVAILABLE
        }
}
