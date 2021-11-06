package com.gustavohisan.apelieuser.feed.mapper

import com.gustavohisan.apelieuser.domain.model.store.CategoryState as DomainState
import com.gustavohisan.apelieuser.feed.model.CategoryState as PresentationState

internal class CategoryStateMapper(private val categoryStateErrorTypeMapper: CategoryStateErrorTypeMapper) {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success(domainState.categories)
            is DomainState.Error -> PresentationState.Error(
                categoryStateErrorTypeMapper.toPresentation(
                    domainState.errorType
                )
            )
        }
}
