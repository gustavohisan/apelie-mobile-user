package com.gustavohisan.apelieuser.register.model

/**
 * Representation of the register check state.
 */
internal sealed class RegisterState {

    /**
     * Represents the register state when it is still waiting for the API result.
     */
    object Checking : RegisterState()

    /**
     * Represents the register state when the user was successfully registered.
     */
    object Success : RegisterState()

    /**
     * Represents the register state when there was an error registering the user.
     *
     * @param errorTypeList list of the errors that were given
     */
    data class Error(val errorTypeList: List<RegisterErrorType>) : RegisterState()
}
