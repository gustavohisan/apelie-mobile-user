package com.gustavohisan.apelieuser.repository.mapper.store

import com.gustavohisan.apelieuser.domain.model.store.Store as DomainStore
import com.gustavohisan.apelieuser.repository.model.store.Store as RepositoryStore

/**
 * Mapper used to map a store from repository to the domain representation.
 */
internal class StoreMapper {

    /**
     * Maps a [RepositoryStore] to a [DomainStore].
     *
     * @param repositoryStore the [RepositoryStore] to be mapped
     *
     * @return the mapped [DomainStore]
     */
    fun toDomain(repositoryStore: RepositoryStore): DomainStore =
        DomainStore(
            storeId = repositoryStore.storeId,
            category = repositoryStore.category,
            state = repositoryStore.state,
            bannerUrl = repositoryStore.bannerUrl,
            theme = repositoryStore.theme,
            city = repositoryStore.city,
            name = repositoryStore.name,
            rating = repositoryStore.rating,
            logoUrl = repositoryStore.logoUrl
        )
}
