package com.gustavohisan.apelieuser.repository.mapper.register

import com.gustavohisan.apelieuser.domain.model.register.RegisterState as DomainState
import com.gustavohisan.apelieuser.repository.model.register.RegisterState as RepositoryState

/**
 * Mapper used to map a [RegisterState] from repository to the domain representation.
 *
 * @param errorTypeMapper mapper used to map the error type
 */
internal class RegisterStateMapper(private val errorTypeMapper: RegisterErrorTypeMapper) {

    /**
     * Maps a [RepositoryState] to a [DomainState].
     *
     * @param repositoryState the [RepositoryState] to be mapped
     *
     * @return the mapped [DomainState]
     */
    fun toDomain(repositoryState: RepositoryState): DomainState =
        when (repositoryState) {
            is RepositoryState.Success -> DomainState.Success
            is RepositoryState.Error -> DomainState.Error(
                listOf(
                    errorTypeMapper.toDomain(repositoryState.errorType)
                )
            )
        }
}
