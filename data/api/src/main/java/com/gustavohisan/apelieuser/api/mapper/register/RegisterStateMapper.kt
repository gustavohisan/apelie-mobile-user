package com.gustavohisan.apelieuser.api.mapper.register

import com.gustavohisan.apelieuser.api.model.register.RegisterState as ApiState
import com.gustavohisan.apelieuser.repository.model.register.RegisterState as RepositoryState

/**
 * Mapper used to map a [RegisterState] from API to the repository representation.
 *
 * @param errorTypeMapper mapper used to map the error type
 */
internal class RegisterStateMapper(private val errorTypeMapper: RegisterErrorTypeMapper) {

    /**
     * Maps a [ApiState] to a [RepositoryState].
     *
     * @param apiState the [ApiState] to be mapped
     *
     * @return the mapped [RepositoryState]
     */
    fun toRepo(apiState: ApiState): RepositoryState =
        when (apiState) {
            is ApiState.Success -> RepositoryState.Success
            is ApiState.Error -> RepositoryState.Error(
                errorTypeMapper.toRepo(
                    apiState.errorType
                )
            )
        }
}
