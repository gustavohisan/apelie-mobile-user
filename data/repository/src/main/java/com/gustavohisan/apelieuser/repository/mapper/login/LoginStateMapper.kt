package com.gustavohisan.apelieuser.repository.mapper.login

import com.gustavohisan.apelieuser.domain.model.login.LoginState as DomainState
import com.gustavohisan.apelieuser.repository.model.login.LoginState as RepositoryState

/**
 * Mapper used to map a [LoginState] from repository to the domain representation.
 *
 * @param errorTypeMapper mapper used to map the error type
 */
internal class LoginStateMapper(private val errorTypeMapper: LoginErrorTypeMapper) {

    /**
     * Maps a [RepositoryState] to a [DomainState].
     *
     * @param repositoryState the [RepositoryState] to be mapped
     *
     * @return the mapped [DomainState]
     */
    fun toDomain(repositoryState: RepositoryState): DomainState =
        when (repositoryState) {
            is RepositoryState.Success -> DomainState.Success(repositoryState.token)
            is RepositoryState.Error -> DomainState.Error(
                listOf(
                    errorTypeMapper.toDomain(repositoryState.errorType)
                )
            )
        }
}
