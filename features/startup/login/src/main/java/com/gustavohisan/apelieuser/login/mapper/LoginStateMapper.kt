package com.gustavohisan.apelieuser.login.mapper

import com.gustavohisan.apelieuser.domain.model.login.LoginState as DomainState
import com.gustavohisan.apelieuser.login.model.LoginState as PresentationState

/**
 * Mapper used to map a [LoginState] from domain to the presentation representation.
 *
 * @param errorTypeMapper mapper used to map the error type
 */
internal class LoginStateMapper(private val errorTypeMapper: LoginErrorTypeMapper) {

    /**
     * Maps a [DomainState] to a [PresentationState].
     *
     * @param domainState the [DomainState] to be mapped
     *
     * @return the mapped [PresentationState]
     */
    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success
            is DomainState.Error -> PresentationState.Error(
                listOf(
                    errorTypeMapper.toPresentation(
                        domainState.errorType
                    )
                )
            )
        }
}
