package com.gustavohisan.apelieuser.repository.model.store

/**
 * Representation of the store.
 */
data class Store(
    val storeId: Int,
    val owner: Owner,
    val products: List<Product>,
    val category: List<String>,
    val state: String,
    val bannerUrl: String,
    val primaryColor: String,
    val secondaryColor: String,
    val city: String,
    val name: String,
    val rating: Float,
    val logoUrl: String,
    val twitter: String,
    val instagram: String,
    val facebook: String,
    val youtube: String,
    val street: String,
    val cep: String,
    val email: String,
    val phone: String,
    val addressNumber: String,
    val neighbourhood: String,
    val description: String
)
