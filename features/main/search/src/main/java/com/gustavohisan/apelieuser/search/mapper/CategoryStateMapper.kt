package com.gustavohisan.apelieuser.search.mapper

import com.gustavohisan.apelieuser.search.model.Filter
import com.gustavohisan.apelieuser.domain.model.store.CategoryState as DomainState
import com.gustavohisan.apelieuser.search.model.CategoryState as PresentationState

internal class CategoryStateMapper(private val categoryStateErrorTypeMapper: CategoryStateErrorTypeMapper) {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success(domainState.categories.map {
                Filter(
                    name = it
                )
            })
            is DomainState.Error -> PresentationState.Error(
                categoryStateErrorTypeMapper.toPresentation(
                    domainState.errorType
                )
            )
        }
}
