package com.gustavohisan.apelieuser.api.mapper.store

import com.gustavohisan.apelieuser.api.model.store.Store as ApiStore
import com.gustavohisan.apelieuser.repository.model.store.Store as RepositoryStore

/**
 * Mapper used to map a store from api to the repository representation.
 */
internal class StoreMapper {

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
            theme = apiStore.theme,
            city = apiStore.city,
            name = apiStore.name,
            rating = apiStore.rating
        )
}
