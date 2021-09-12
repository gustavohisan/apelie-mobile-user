package com.gustavohisan.apelieuser.login.model

/**
 * Representation of the login check state.
 */
internal sealed class LoginState {

    /**
     * Represents the login state when it is still waiting for the API result.
     */
    object Checking : LoginState()

    /**
     * Represents the login state when the login was validated successfully.
     */
    object Success : LoginState()

    /**
     * Represents the login state when there was an error validating the login.
     *
     * @param errorTypeList list of the errors that were given
     */
    data class Error(val errorTypeList: List<LoginErrorType>) : LoginState()

    object Default : LoginState()
}
