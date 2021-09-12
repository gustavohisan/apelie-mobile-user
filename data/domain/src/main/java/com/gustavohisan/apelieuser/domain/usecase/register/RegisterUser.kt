package com.gustavohisan.apelieuser.domain.usecase.register

import com.gustavohisan.apelieuser.core.extensions.isValidEmail
import com.gustavohisan.apelieuser.core.extensions.isValidName
import com.gustavohisan.apelieuser.core.extensions.isValidPassword
import com.gustavohisan.apelieuser.domain.model.register.RegisterErrorType
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
    suspend operator fun invoke(
        email: String,
        password: String,
        name: String,
        confirmPassword: String
    ): RegisterState {
        val errors = checkFields(email, password, confirmPassword, name)
        return if (errors.isNotEmpty()) {
            RegisterState.Error(errors)
        } else {
            userApiRepository.registerUser(email, password, name)
        }
    }

    private fun checkFields(
        email: String,
        password: String,
        confirmPassword: String,
        name: String
    ): List<RegisterErrorType> {
        val errors: MutableList<RegisterErrorType> = mutableListOf()
        if (email.isValidEmail().not()) {
            errors.add(RegisterErrorType.INVALID_EMAIL)
        }
        if (password.isValidPassword().not()) {
            errors.add(RegisterErrorType.INVALID_PASSWORD)
        }
        if (password != confirmPassword) {
            errors.add(RegisterErrorType.PASSWORDS_DONT_MATCH)
        }
        if (name.isValidName().not()) {
            errors.add(RegisterErrorType.INVALID_NAME)
        }
        return errors
    }
}
