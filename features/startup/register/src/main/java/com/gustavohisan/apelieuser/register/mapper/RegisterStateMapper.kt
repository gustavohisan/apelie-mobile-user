package com.gustavohisan.apelieuser.register.mapper

import com.gustavohisan.apelieuser.domain.model.register.RegisterState as DomainState
import com.gustavohisan.apelieuser.register.model.RegisterState as PresentationState

/**
 * Mapper used to map a [RegisterState] from domain to the presentation representation.
 *
 * @param errorTypeMapper mapper used to map the error type
 */
internal class RegisterStateMapper(private val errorTypeMapper: RegisterErrorTypeMapper) {

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
