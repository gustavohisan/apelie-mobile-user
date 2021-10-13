package com.gustavohisan.apelieuser.search.mapper

import com.gustavohisan.apelieuser.domain.model.store.MainScreenStoreErrorType as DomainStoreErrorType
import com.gustavohisan.apelieuser.search.model.SearchStoreErrorType as PresentationStoreErrorType

/**
 * Mapper used to map a store error type from domain to the presentation representation.
 */
internal class SearchStoreErrorTypeMapper {

    /**
     * Maps a [DomainStoreErrorType] to a [PresentationStoreErrorType].
     *
     * @param domainStoreErrorType the [DomainStoreErrorType] to be mapped
     *
     * @return the mapped [PresentationStoreErrorType]
     */
    fun toPresentation(domainStoreErrorType: DomainStoreErrorType): PresentationStoreErrorType =
        when (domainStoreErrorType) {
            DomainStoreErrorType.SERVER_UNAVAILABLE -> PresentationStoreErrorType.SERVER_UNAVAILABLE
            DomainStoreErrorType.EMPTY_LIST -> PresentationStoreErrorType.EMPTY_LIST
        }
}
