package com.gustavohisan.apelieuser.domain.usecase.register

import com.gustavohisan.apelieuser.domain.model.register.RegisterState
import com.gustavohisan.apelieuser.domain.repository.user.UserApiRepository

/**
 * Use-case used to subscribe a new user to the system.
 *
 * @param userApiRepository repository used to register the new user in the database
 */
class RegisterUser(private val userApiRepository: UserApiRepository) {

    /**
     * Subscribes a new user in the system, if the user is already subscribed or an error occurs
     * return an error, else returns that the user was successfully subscribed.
     *
     * @param email a [String] containing the email
     * @param password a [String] containing the password
     * @param name a [String] containing the name
     *
     * @return a [RegisterState] with the result
     */
    suspend operator fun invoke(email: String, password: String, name: String): RegisterState =
        userApiRepository.registerUser(email, password, name)
}
