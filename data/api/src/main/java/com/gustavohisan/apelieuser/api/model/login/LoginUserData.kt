package com.gustavohisan.apelieuser.api.model.login

/**
 * Representation of the user login data to be used in the API request.
 *
 * @param username the user username
 * @param password the user password
 */
internal data class LoginUserData(
    val username: String,
    val password: String
)
