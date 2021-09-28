package com.gustavohisan.apelieuser.store.mapper

import com.gustavohisan.apelieuser.domain.model.store.StoreState as DomainStoreState
import com.gustavohisan.apelieuser.store.model.StoreState as PresentationStoreState

internal class StoreStateMapper(
    private val storeMapper: StoreMapper,
    private val storeErrorTypeMapper: StoreErrorTypeMapper
) {

    fun toPresentation(domainStoreState: DomainStoreState): PresentationStoreState =
        when (domainStoreState) {
            is DomainStoreState.Success ->
                PresentationStoreState.Success(storeMapper.toPresentation(domainStoreState.store))
            is DomainStoreState.Error ->
                PresentationStoreState.Error(storeErrorTypeMapper.toPresentation(domainStoreState.errorType))
        }
}
