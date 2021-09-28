package com.gustavohisan.apelieuser.store.mapper

import com.gustavohisan.apelieuser.store.model.Store as PresentationStore
import com.gustavohisan.apelieuser.domain.model.store.Store as DomainStore

internal class StoreMapper(
    private val ownerMapper: OwnerMapper,
    private val productCategoryMapper: ProductCategoryMapper
) {

    fun toPresentation(domainStore: DomainStore): PresentationStore =
        PresentationStore(
            storeId = domainStore.storeId,
            products = domainStore.products.map { category ->
                productCategoryMapper.toPresentation(category)
            },
            category = domainStore.category,
            state = domainStore.state,
            bannerUrl = domainStore.bannerUrl,
            primaryColor = domainStore.primaryColor,
            secondaryColor = domainStore.secondaryColor,
            city = domainStore.city,
            name = domainStore.name,
            rating = domainStore.rating,
            logoUrl = domainStore.logoUrl,
            email = domainStore.email,
            addressNumber = domainStore.addressNumber,
            cep = domainStore.cep,
            description = domainStore.description,
            facebook = domainStore.facebook,
            instagram = domainStore.instagram,
            neighbourhood = domainStore.neighbourhood,
            owner = ownerMapper.toPresentation(domainStore.owner),
            phone = domainStore.phone,
            street = domainStore.street,
            twitter = domainStore.twitter,
            youtube = domainStore.youtube
        )
}
