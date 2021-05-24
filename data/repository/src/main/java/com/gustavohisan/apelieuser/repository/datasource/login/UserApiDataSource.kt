package com.gustavohisan.apelieuser.repository.datasource.login

import com.gustavohisan.apelieuser.repository.model.login.LoginState
import com.gustavohisan.apelieuser.repository.model.register.RegisterState

/**
 * User data source used to provide information with the API.
 */
interface UserApiDataSource {

    /**
     * Checks whether a given email and password are subscribed in the system.
     *
     * @param email a [String] with the email
     * @param password a [String] with the password
     *
     * @return the [LoginState] with the result
     */
    suspend fun validateLogin(email: String, password: String): LoginState

    /**
     * Checks if the given token is valid or not.
     *
     * @param token a [String] with the token to be checked
     *
     * @return if the token is valid
     */
    suspend fun validateToken(token: String): Boolean

    /**
     * Subscribes the user in the database.
     *
     * @param email a [String] containing the email
     * @param password a [String] containing the password
     * @param name a [String] containing the name
     *
     * @return a [RegisterState] with the result
     */
    suspend fun subscribeUser(email: String, password: String, name: String): RegisterState
}
