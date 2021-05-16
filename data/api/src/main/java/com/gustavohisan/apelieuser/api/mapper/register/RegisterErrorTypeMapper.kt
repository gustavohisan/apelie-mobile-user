package com.gustavohisan.apelieuser.api.mapper.register

import com.gustavohisan.apelieuser.api.model.register.RegisterErrorType as ApiErrorType
import com.gustavohisan.apelieuser.repository.model.register.RegisterErrorType as RepositoryErrorType

/**
 * Mapper used to map an [RegisterErrorType] from API to the repository representation.
 */
internal class RegisterErrorTypeMapper {

    /**
     * Maps a [ApiErrorType] to a [RepositoryErrorType].
     *
     * @param apiErrorType the [ApiErrorType] to be mapped
     *
     * @return the mapped [RepositoryErrorType]
     */
    fun toRepo(apiErrorType: ApiErrorType): RepositoryErrorType =
        when (apiErrorType) {
            ApiErrorType.ALREADY_SUBSCRIBED -> RepositoryErrorType.ALREADY_SUBSCRIBED
            ApiErrorType.SERVER_UNAVAILABLE -> RepositoryErrorType.SERVER_UNAVAILABLE
        }
}
