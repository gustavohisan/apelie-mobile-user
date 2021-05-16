package com.gustavohisan.apelieuser.repository.mapper.login

import com.gustavohisan.apelieuser.domain.model.login.LoginErrorType as DomainErrorType
import com.gustavohisan.apelieuser.repository.model.login.LoginErrorType as RepositoryErrorType

/**
 * Mapper used to map an [LoginErrorType] from repository to the domain representation.
 */
internal class LoginErrorTypeMapper {

    /**
     * Maps a [RepositoryErrorType] to a [DomainErrorType].
     *
     * @param repositoryErrorType the [RepositoryErrorType] to be mapped
     *
     * @return the mapped [DomainErrorType]
     */
    fun toDomain(repositoryErrorType: RepositoryErrorType): DomainErrorType =
        when (repositoryErrorType) {
            RepositoryErrorType.WRONG_PASSWORD -> DomainErrorType.WRONG_PASSWORD
            RepositoryErrorType.NOT_SUBSCRIBED -> DomainErrorType.NOT_SUBSCRIBED
            RepositoryErrorType.SERVER_UNAVAILABLE -> DomainErrorType.SERVER_UNAVAILABLE
        }
}
