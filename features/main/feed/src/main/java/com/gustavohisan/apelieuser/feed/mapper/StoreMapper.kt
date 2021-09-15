package com.gustavohisan.apelieuser.feed.mapper

import com.gustavohisan.apelieuser.domain.model.store.Store as DomainStore
import com.gustavohisan.apelieuser.feed.model.Store as PresentationStore

/**
 * Mapper used to map a store from domain to the presentation representation.
 */
internal class StoreMapper {

    /**
     * Maps a [DomainStore] to a [PresentationStore].
     *
     * @param domainStore the [DomainStore] to be mapped
     *
     * @return the mapped [PresentationStore]
     */
    fun toPresentation(domainStore: DomainStore): PresentationStore =
        PresentationStore(
            storeId = domainStore.storeId,
            category = domainStore.category,
            state = domainStore.state,
            bannerUrl = domainStore.bannerUrl,
            primaryColor = domainStore.primaryColor,
            secondaryColor = domainStore.secondaryColor,
            city = domainStore.city,
            name = domainStore.name,
            rating = domainStore.rating,
            logoUrl = domainStore.logoUrl
        )
}
