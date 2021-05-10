package com.gustavohisan.apelieuser.api.mapper.login

import com.gustavohisan.apelieuser.api.model.login.LoginState as ApiState
import com.gustavohisan.apelieuser.repository.model.login.LoginState as RepositoryState

/**
 * Mapper used to map a [LoginState] from API to the repository representation.
 *
 * @param errorTypeMapper mapper used to map the error type
 */
internal class LoginStateMapper(private val errorTypeMapper: LoginErrorTypeMapper) {

    /**
     * Maps a [ApiState] to a [RepositoryState].
     *
     * @param apiState the [ApiState] to be mapped
     *
     * @return the mapped [RepositoryState]
     */
    fun toRepo(apiState: ApiState): RepositoryState =
        when (apiState) {
            is ApiState.Success -> RepositoryState.Success(apiState.token)
            is ApiState.Error -> RepositoryState.Error(
                errorTypeMapper.toRepo(
                    apiState.errorType
                )
            )
        }
}
