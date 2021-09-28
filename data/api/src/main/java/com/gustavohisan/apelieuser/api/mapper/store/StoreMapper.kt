package com.gustavohisan.apelieuser.api.mapper.store

import com.gustavohisan.apelieuser.api.model.store.Store as ApiStore
import com.gustavohisan.apelieuser.repository.model.store.Store as RepositoryStore

internal class StoreMapper(
    private val ownerMapper: OwnerMapper,
    private val productMapper: ProductMapper
) {

    fun toRepo(apiStore: ApiStore): RepositoryStore =
        RepositoryStore(
            storeId = apiStore.storeId,
            products = apiStore.products?.map { product -> productMapper.toRepo(product) }
                ?: listOf(),
            category = apiStore.category,
            state = apiStore.state,
            bannerUrl = apiStore.bannerUrl,
            primaryColor = apiStore.primaryColor,
            secondaryColor = apiStore.secondaryColor,
            city = apiStore.city,
            name = apiStore.name,
            rating = apiStore.rating,
            logoUrl = apiStore.logoUrl,
            email = apiStore.email,
            addressNumber = apiStore.addressNumber,
            cep = apiStore.cep,
            description = apiStore.description,
            facebook = apiStore.facebook,
            instagram = apiStore.instagram,
            neighbourhood = apiStore.neighbourhood,
            owner = ownerMapper.toRepo(apiStore.owner),
            phone = apiStore.phone,
            street = apiStore.street,
            twitter = apiStore.twitter,
            youtube = apiStore.youtube
        )
}
