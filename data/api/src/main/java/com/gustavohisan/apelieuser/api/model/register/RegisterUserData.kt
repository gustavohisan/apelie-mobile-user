package com.gustavohisan.apelieuser.api.model.register

/**
 * Representation of the user data to be used in the API request.
 *
 * @param fullName the user full name
 * @param email the user email
 * @param password the user password
 * @param gender the user gender
 * @param birthDate the user birth date
 */
data class RegisterUserData(
    val fullName: String,
    val email: String,
    val password: String,
    val gender: String,
    val birthDate: String
)
