package com.gustavohisan.apelieuser.api.endpoints.user

import com.gustavohisan.apelieuser.api.model.address.Address
import com.gustavohisan.apelieuser.api.model.address.AddressData
import com.gustavohisan.apelieuser.api.model.address.EditAddressData
import com.gustavohisan.apelieuser.api.model.login.LoginUserData
import com.gustavohisan.apelieuser.api.model.orders.Order
import com.gustavohisan.apelieuser.api.model.orders.Rate
import com.gustavohisan.apelieuser.api.model.register.RegisterUserData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

/**
 * User end points.
 */
internal interface UserEndpoints {

    /**
     * Checks if the user login is subscribed in the database.
     *
     * @param userData the user data to be validated
     *
     * @return a [Response] with the [ResponseBody] containing the request state
     */
    @POST("login")
    suspend fun validateUserLogin(@Body userData: LoginUserData): Response<ResponseBody>

    /**
     * Checks if the user token is valid.
     *
     * @param token the token to be validated
     *
     * @return a [Response] with the [ResponseBody] containing the request state
     */
    @GET("users/me")
    suspend fun validateUserToken(@Header("Authorization") token: String): Response<ResponseBody>

    /**
     * Inserts a new user in the database.
     *
     * @param userData the user data to be inserted
     *
     * @return a [Response] with the [ResponseBody] containing the request state
     */
    @POST("users")
    suspend fun insertUser(@Body userData: RegisterUserData): Response<ResponseBody>

    @POST("users/addresses")
    suspend fun insertAddress(@Body addressData: AddressData): Response<ResponseBody>

    @DELETE("users/addresses/{id}")
    suspend fun deleteAddress(@Path("id") id: Int): Response<ResponseBody>

    @PUT("users/addresses")
    suspend fun editAddress(@Body editAddressData: EditAddressData): Response<ResponseBody>

    @GET("users/orders")
    suspend fun getOrders(): Response<List<Order>>

    @GET("users/addresses")
    suspend fun getUserAddresses(): Response<List<Address>>

    @GET("orders/{orderId}")
    suspend fun getOrderById(@Path("orderId") id: Int): Response<Order>

    @POST("orders/{orderId}/reviews")
    suspend fun rateOrder(@Path("orderId") id: Int, @Body rate: Rate): Response<ResponseBody>
}
