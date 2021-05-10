package com.gustavohisan.apelieuser.repository.model.login

/**
 * Representation of the login check state.
 */
sealed class LoginState {

    /**
     * Represents the login state when the login was validated successfully and the user token was
     * retrieved.
     *
     * @param token the user token
     */
    data class Success(val token: Int) : LoginState()

    /**
     * Represents the login state when there was an error validating the login.
     *
     * @param errorType type of the error that was given
     */
    data class Error(val errorType: LoginErrorType) : LoginState()
}
