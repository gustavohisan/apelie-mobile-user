package com.gustavohisan.apelieuser.product.mapper

import com.gustavohisan.apelieuser.domain.model.store.ProductState as DomainState
import com.gustavohisan.apelieuser.product.model.ProductState as PresentationState

internal class ProductStateMapper(
    private val productMapper: ProductMapper,
    private val productErrorTypeMapper: ProductErrorTypeMapper
) {

    fun toPresentation(domainState: DomainState): PresentationState =
        when (domainState) {
            is DomainState.Success -> PresentationState.Success(
                productMapper.toPresentation(
                    domainState.product
                )
            )
            is DomainState.Error -> PresentationState.Error(
                productErrorTypeMapper.toPresentation(
                    domainState.errorType
                )
            )
        }
}
