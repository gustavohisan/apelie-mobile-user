package com.gustavohisan.apelieuser.feed.mapper

import com.gustavohisan.apelieuser.domain.model.store.MainScreenStoreState as DomainStoreState
import com.gustavohisan.apelieuser.feed.model.MainScreenStoreState as PresentationStoreState

/**
 * Mapper used to map the store state from repository to domain representation.
 *
 * @param storeMapper mapper used to map a store
 * @param storeErrorTypeMapper mapper used to map a store error type
 */
internal class MainScreenStoreStateMapper(
    private val storeMapper: MainScreenStoreMapper,
    private val storeErrorTypeMapper: MainScreenStoreErrorTypeMapper
) {

    /**
     * Maps a [DomainStoreState] to a [PresentationStoreState].
     *
     * @param domainStoreState the [DomainStoreState] to be mapped
     *
     * @return the mapped [PresentationStoreState]
     */
    fun toPresentation(domainStoreState: DomainStoreState): PresentationStoreState =
        when (domainStoreState) {
            is DomainStoreState.Success ->
                PresentationStoreState.Success(
                    domainStoreState.storeList?.map { store ->
                        storeMapper.toPresentation(store)
                    }.orEmpty()
                )
            is DomainStoreState.Error ->
                PresentationStoreState.Error(
                    storeErrorTypeMapper.toPresentation(domainStoreState.errorType)
                )
        }
}
