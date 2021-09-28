package com.gustavohisan.apelieuser.repository.mapper.store

import com.gustavohisan.apelieuser.repository.model.store.Product
import com.gustavohisan.apelieuser.domain.model.store.Store as DomainStore
import com.gustavohisan.apelieuser.repository.model.store.Store as RepositoryStore

/**
 * Mapper used to map a store from repository to the domain representation.
 */
internal class StoreMapper(
    private val ownerMapper: OwnerMapper,
    private val productMapper: ProductMapper
) {

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
            products = productMapper.toDomain(repositoryStore.products),
            category = repositoryStore.category,
            state = repositoryStore.state,
            bannerUrl = repositoryStore.bannerUrl,
            primaryColor = repositoryStore.primaryColor,
            secondaryColor = repositoryStore.secondaryColor,
            city = repositoryStore.city,
            name = repositoryStore.name,
            rating = repositoryStore.rating,
            logoUrl = repositoryStore.logoUrl,
            email = repositoryStore.email,
            addressNumber = repositoryStore.addressNumber,
            cep = repositoryStore.cep,
            description = repositoryStore.description,
            facebook = repositoryStore.facebook,
            instagram = repositoryStore.instagram,
            neighbourhood = repositoryStore.neighbourhood,
            owner = ownerMapper.toDomain(repositoryStore.owner),
            phone = repositoryStore.phone,
            street = repositoryStore.street,
            twitter = repositoryStore.twitter,
            youtube = repositoryStore.youtube
        )
}
