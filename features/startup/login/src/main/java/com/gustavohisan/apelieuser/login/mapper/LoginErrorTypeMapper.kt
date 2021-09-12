package com.gustavohisan.apelieuser.login.mapper

import com.gustavohisan.apelieuser.domain.model.login.LoginErrorType as DomainErrorType
import com.gustavohisan.apelieuser.login.model.LoginErrorType as PresentationErrorType

/**
 * Mapper used to map an [LoginErrorType] from domain to the presentation representation.
 */
internal class LoginErrorTypeMapper {

    /**
     * Maps a [DomainErrorType] to a [PresentationErrorType].
     *
     * @param domainErrorType the [DomainErrorType] to be mapped
     *
     * @return the mapped [PresentationErrorType]
     */
    fun toPresentation(domainErrorType: DomainErrorType): PresentationErrorType =
        when (domainErrorType) {
            DomainErrorType.WRONG_PASSWORD -> PresentationErrorType.WRONG_PASSWORD
            DomainErrorType.NOT_SUBSCRIBED -> PresentationErrorType.NOT_SUBSCRIBED
            DomainErrorType.SERVER_UNAVAILABLE -> PresentationErrorType.SERVER_UNAVAILABLE
            DomainErrorType.INVALID_PASSWORD -> PresentationErrorType.INVALID_PASSWORD
            DomainErrorType.INVALID_EMAIL -> PresentationErrorType.INVALID_EMAIL
        }
}
