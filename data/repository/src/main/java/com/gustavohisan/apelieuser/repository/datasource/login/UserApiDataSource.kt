package com.gustavohisan.apelieuser.repository.datasource.login

import com.gustavohisan.apelieuser.repository.model.login.LoginState

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
    fun validateLogin(email: String, password: String): LoginState
}
