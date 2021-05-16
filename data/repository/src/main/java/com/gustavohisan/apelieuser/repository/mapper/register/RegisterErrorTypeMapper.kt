package com.gustavohisan.apelieuser.repository.mapper.register

import com.gustavohisan.apelieuser.domain.model.register.RegisterErrorType as DomainErrorType
import com.gustavohisan.apelieuser.repository.model.register.RegisterErrorType as RepoErrorType

/**
 * Mapper used to map an [RegisterErrorType] from repository to the domain representation.
 */
internal class RegisterErrorTypeMapper {

    /**
     * Maps a [RepoErrorType] to a [DomainErrorType].
     *
     * @param repositoryErrorType the [RepoErrorType] to be mapped
     *
     * @return the mapped [DomainErrorType]
     */
    fun toDomain(repositoryErrorType: RepoErrorType): DomainErrorType =
        when (repositoryErrorType) {
            RepoErrorType.ALREADY_SUBSCRIBED -> DomainErrorType.ALREADY_SUBSCRIBED
            RepoErrorType.SERVER_UNAVAILABLE -> DomainErrorType.SERVER_UNAVAILABLE
        }
}
