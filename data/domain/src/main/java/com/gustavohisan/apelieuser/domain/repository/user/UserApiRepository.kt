package com.gustavohisan.apelieuser.domain.repository.user

import com.gustavohisan.apelieuser.domain.model.login.LoginState

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
    fun validateUserLogin(email: String, password: String): LoginState

    /**
     * Checks if the given token is valid or not.
     *
     * @param token a [Int] with the token to be checked
     *
     * @return if the token is valid
     */
    fun validateUserToken(token: Int): Boolean
}
