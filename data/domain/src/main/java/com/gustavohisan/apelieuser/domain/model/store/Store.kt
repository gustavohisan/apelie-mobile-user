package com.gustavohisan.apelieuser.domain.model.store

/**
 * Representation of the store.
 *
 * @param storeId the id of the store
 * @param category the category of the store
 * @param state the state address of the store
 * @param bannerUrl the image url for the store banner
 * @param theme the store theme
 * @param city the store city address
 * @param name the store name
 * @param rating the rating of the store
 * @param logoUrl the image url for the store logo
 */
data class Store(
    val storeId: Int,
    val category: String,
    val state: String,
    val bannerUrl: String,
    val theme: String,
    val city: String,
    val name: String,
    val rating: Float,
    val logoUrl: String
)
