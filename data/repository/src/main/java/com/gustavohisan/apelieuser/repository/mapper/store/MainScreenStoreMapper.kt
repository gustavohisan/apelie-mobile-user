package com.gustavohisan.apelieuser.repository.mapper.store

import com.gustavohisan.apelieuser.domain.model.store.MainScreenStore as DomainStore
import com.gustavohisan.apelieuser.repository.model.store.MainScreenStore as RepositoryStore

/**
 * Mapper used to map a store from repository to the domain representation.
 */
internal class MainScreenStoreMapper {

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
            primaryColor = repositoryStore.primaryColor,
            secondaryColor = repositoryStore.secondaryColor,
            city = repositoryStore.city,
            name = repositoryStore.name,
            rating = repositoryStore.rating,
            logoUrl = repositoryStore.logoUrl
        )
}
