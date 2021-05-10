package com.gustavohisan.apelieuser.api.mapper.login

import com.gustavohisan.apelieuser.api.model.login.LoginErrorType as ApiErrorType
import com.gustavohisan.apelieuser.repository.model.login.LoginErrorType as RepositoryErrorType

/**
 * Mapper used to map an [LoginErrorType] from API to the repository representation.
 */
internal class LoginErrorTypeMapper {

    /**
     * Maps a [ApiErrorType] to a [RepositoryErrorType].
     *
     * @param apiErrorType the [ApiErrorType] to be mapped
     *
     * @return the mapped [RepositoryErrorType]
     */
    fun toRepo(apiErrorType: ApiErrorType): RepositoryErrorType =
        when (apiErrorType) {
            ApiErrorType.WRONG_PASSWORD -> RepositoryErrorType.WRONG_PASSWORD
            ApiErrorType.NOT_SUBSCRIBED -> RepositoryErrorType.NOT_SUBSCRIBED
            ApiErrorType.SERVER_UNAVAILABLE -> RepositoryErrorType.SERVER_UNAVAILABLE
        }
}
