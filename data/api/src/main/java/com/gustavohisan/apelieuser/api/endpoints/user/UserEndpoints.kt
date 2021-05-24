package com.gustavohisan.apelieuser.api.endpoints.user

import com.gustavohisan.apelieuser.api.model.login.LoginUserData
import com.gustavohisan.apelieuser.api.model.register.RegisterUserData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * User end points.
 */
interface UserEndpoints {

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
    @GET("user/me")
    suspend fun validateUserToken(@Header("Authorization") token: String): Response<ResponseBody>

    /**
     * Inserts a new user in the database.
     *
     * @param userData the user data to be inserted
     *
     * @return a [Response] with the [ResponseBody] containing the request state
     */
    @POST("user")
    suspend fun insertUser(@Body userData: RegisterUserData): Response<ResponseBody>
}