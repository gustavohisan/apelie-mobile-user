package com.gustavohisan.apelieuser.api.mapper.store

import com.gustavohisan.apelieuser.api.model.store.MainScreenStore as ApiStore
import com.gustavohisan.apelieuser.repository.model.store.MainScreenStore as RepositoryStore

/**
 * Mapper used to map a store from api to the repository representation.
 */
internal class MainScreenStoreMapper {

    /**
     * Maps a [ApiStore] to a [RepositoryStore].
     *
     * @param apiStore the [ApiStore] to be mapped
     *
     * @return the mapped [RepositoryStore]
     */
    fun toRepository(apiStore: ApiStore): RepositoryStore =
        RepositoryStore(
            storeId = apiStore.storeId,
            category = apiStore.category,
            state = apiStore.state,
            bannerUrl = apiStore.bannerUrl,
            primaryColor = apiStore.primaryColor,
            secondaryColor = apiStore.secondaryColor,
            city = apiStore.city,
            name = apiStore.name,
            rating = apiStore.rating,
            logoUrl = apiStore.logoUrl
        )
}
