package com.gustavohisan.apelieuser.register.mapper

import com.gustavohisan.apelieuser.domain.model.register.RegisterErrorType as DomainErrorType
import com.gustavohisan.apelieuser.register.model.RegisterErrorType as PresentationErrorType

/**
 * Mapper used to map an [RegisterErrorType] from domain to the presentation representation.
 */
internal class RegisterErrorTypeMapper {

    /**
     * Maps a [DomainErrorType] to a [PresentationErrorType].
     *
     * @param domainErrorType the [DomainErrorType] to be mapped
     *
     * @return the mapped [PresentationErrorType]
     */
    fun toPresentation(domainErrorType: DomainErrorType): PresentationErrorType =
        when (domainErrorType) {
            DomainErrorType.ALREADY_SUBSCRIBED -> PresentationErrorType.ALREADY_SUBSCRIBED
            DomainErrorType.SERVER_UNAVAILABLE -> PresentationErrorType.SERVER_UNAVAILABLE
        }
}
