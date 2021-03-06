package com.gustavohisan.apelieuser.domain.usecase.login

import com.gustavohisan.apelieuser.core.extensions.isValidEmail
import com.gustavohisan.apelieuser.core.extensions.isValidPassword
import com.gustavohisan.apelieuser.domain.model.login.LoginErrorType
import com.gustavohisan.apelieuser.domain.model.login.LoginState
import com.gustavohisan.apelieuser.domain.repository.user.UserApiRepository
import com.gustavohisan.apelieuser.domain.repository.user.UserStorageRepository

/**
 * Use-case used to validate a given login.
 *
 * @param userApiRepository repository used to check the login information
 * @param userStorageRepository repository used to store the users token
 */
class ValidateLogin(
    private val userApiRepository: UserApiRepository,
    private val userStorageRepository: UserStorageRepository
) {

    /**
     * Checks whether the given email and password are subscribed in the system, if so stores the
     * user token.
     *
     * @param email a [String] containing the email
     * @param password a [String] containing the password
     *
     * @return a [LoginState] with the result
     */
    suspend operator fun invoke(email: String, password: String): LoginState {
        val errors = checkFields(email, password)
        if (errors.isNotEmpty()) {
            return LoginState.Error(errors)
        }
        val state = userApiRepository.validateUserLogin(email, password)
        return if (state is LoginState.Success) {
            userApiRepository.setUserToken(state.token)
            storeToken(state.token)
        } else {
            state
        }
    }

    /**
     * Stores the given token in the device.
     *
     * @param token a [String] containing the user token
     *
     * @return the [LoginState] resulting in a successful validation
     */
    private fun storeToken(token: String): LoginState {
        userStorageRepository.storeUserToken(token)
        return LoginState.Success("")
    }

    private fun checkFields(email: String, password: String): List<LoginErrorType> {
        val errors: MutableList<LoginErrorType> = mutableListOf()
        if (email.isValidEmail().not()) {
            errors.add(LoginErrorType.INVALID_EMAIL)
        }
        if (password.isValidPassword().not()) {
            errors.add(LoginErrorType.INVALID_PASSWORD)
        }
        return errors
    }
}
