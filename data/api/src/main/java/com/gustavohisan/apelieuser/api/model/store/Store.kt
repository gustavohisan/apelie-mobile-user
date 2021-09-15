package com.gustavohisan.apelieuser.api.model.store

import com.google.gson.annotations.SerializedName

/**
 * Representation of the store retrieved by the API.
 *
 * @param storeId the id of the store
 * @param category the category of the store
 * @param state the state address of the store
 * @param bannerUrl the image url for the store banner
 * @param primaryColor the store primary color
 * @param secondaryColor the store secondary color
 * @param city the store city address
 * @param name the store name
 * @param rating the rating of the store
 * @param logoUrl the image url for the store logo
 */
data class Store(
    @SerializedName("storeId")
    val storeId: Int,
    @SerializedName("category")
    val category: List<String>,
    @SerializedName("state")
    val state: String,
    @SerializedName("bannerUrl")
    val bannerUrl: String,
    @SerializedName("primaryColor")
    val primaryColor: String,
    @SerializedName("secondaryColor")
    val secondaryColor: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("logoUrl")
    val logoUrl: String
)
