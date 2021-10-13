package com.gustavohisan.apelieuser.api.endpoints.store

import com.gustavohisan.apelieuser.api.model.product.Product
import com.gustavohisan.apelieuser.api.model.store.MainScreenStore
import com.gustavohisan.apelieuser.api.model.store.Store
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * End points of the store to be used in the API requests.
 */
internal interface StoreEndpoints {

    /**
     * Gets the front page stores to be shown on the home screen.
     *
     * @return a [Response] with a [List] of [MainScreenStore] or an error code
     */
    @GET("stores")
    suspend fun getFrontPageStores(): Response<List<MainScreenStore>>

    @GET("stores/{storeId}")
    suspend fun getStoreData(@Path("storeId") id: Int): Response<Store>

    @GET("stores")
    suspend fun searchStores(
        @Query("name") query: String,
        @Query("categories") categories: String?
    ): Response<List<MainScreenStore>>

    @GET("products/{productId}")
    suspend fun getProductData(@Path("productId") id: Int): Response<Product>

    @GET("stores/categories")
    suspend fun getCategories(): Response<List<String>>
}
