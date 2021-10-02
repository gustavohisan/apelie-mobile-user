package com.gustavohisan.apelieuser.api.model.store

import com.google.gson.annotations.SerializedName
import com.gustavohisan.apelieuser.api.model.product.Product

/**
 * Representation of the store.
 */
internal data class Store(
    @SerializedName("storeId")
    val storeId: Int,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("products")
    val products: List<Product>?,
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
    val logoUrl: String,
    @SerializedName("twitterAccount")
    val twitter: String,
    @SerializedName("instagramAccount")
    val instagram: String,
    @SerializedName("facebookAccount")
    val facebook: String,
    @SerializedName("youtubeAccount")
    val youtube: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("cep")
    val cep: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("addressNumber")
    val addressNumber: String,
    @SerializedName("neighbourhood")
    val neighbourhood: String,
    @SerializedName("description")
    val description: String
)

