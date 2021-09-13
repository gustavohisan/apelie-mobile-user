package com.gustavohisan.apelieuser.api.model.register

/**
 * Representation of the user data to be used in the API request.
 *
 * @param fullName the user full name
 * @param email the user email
 * @param password the user password
 * @param photo the user photo
 */
data class RegisterUserData(
    val fullName: String,
    val email: String,
    val password: String,
    val photo: String
)
