package com.gustavohisan.apelieuser.api.endpoints.store

import com.gustavohisan.apelieuser.api.model.store.MainScreenStore
import com.gustavohisan.apelieuser.api.model.store.Store
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

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
}
