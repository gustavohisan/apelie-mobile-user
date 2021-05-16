package com.gustavohisan.apelieuser.repository.model.register

/**
 * Representation of the register state.
 */
sealed class RegisterState {

    /**
     * Represents the register state when the user was successfully registered.
     */
    object Success : RegisterState()

    /**
     * Represents the register state when there was an error registering the user.
     *
     * @param errorType the error that occurred when trying to subscribe the user
     */
    data class Error(val errorType: RegisterErrorType) : RegisterState()
}
