package com.gustavohisan.apelieuser.api.endpoints.store

import com.gustavohisan.apelieuser.api.model.store.Store
import retrofit2.Response
import retrofit2.http.GET

/**
 * End points of the store to be used in the API requests.
 */
interface StoreEndpoints {

    /**
     * Gets the front page stores to be shown on the home screen.
     *
     * @return a [Response] with a [List] of [Store] or an error code
     */
    @GET("store")
    suspend fun getFrontPageStores(): Response<List<Store>>
}
