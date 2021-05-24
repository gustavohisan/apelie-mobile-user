package com.gustavohisan.apelieuser.domain.repository.user

import com.gustavohisan.apelieuser.domain.model.login.LoginState
import com.gustavohisan.apelieuser.domain.model.register.RegisterState

/**
 * User repository used to provide user information using the API.
 */
interface UserApiRepository {

    /**
     * Checks whether a given email and password are subscribed in the system.
     *
     * @param email a [String] with the email
     * @param password a [String] with the password
     *
     * @return the [LoginState] with the result
     */
    suspend fun validateUserLogin(email: String, password: String): LoginState

    /**
     * Checks if the given token is valid or not.
     *
     * @param token a [String] with the token to be checked
     *
     * @return if the token is valid
     */
    suspend fun validateUserToken(token: String): Boolean

    /**
     * Subscribes a new user in the database and returns the result state of the subscription.
     *
     * @param email a [String] with the email
     * @param password a [String] with the password
     * @param name a [String] with the name
     *
     * @return the [RegisterState] with the result
     */
    suspend fun registerUser(email: String, password: String, name: String): RegisterState
}
