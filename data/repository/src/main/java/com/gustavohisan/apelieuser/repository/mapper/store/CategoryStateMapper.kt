package com.gustavohisan.apelieuser.repository.mapper.store

import com.gustavohisan.apelieuser.domain.model.store.CategoryErrorType
import com.gustavohisan.apelieuser.domain.model.store.CategoryState as DomainState
import com.gustavohisan.apelieuser.repository.model.store.CategoryState as RepoState

internal class CategoryStateMapper {

    fun toDomain(repoState: RepoState): DomainState =
        when (repoState) {
            is RepoState.Success -> DomainState.Success(repoState.categories)
            is RepoState.Error -> DomainState.Error(CategoryErrorType.SERVER_UNAVAILABLE)
        }
}
