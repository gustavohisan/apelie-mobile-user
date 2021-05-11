package com.gustavohisan.apelieuser.domain.usecase.login

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
    operator fun invoke(email: String, password: String): LoginState {
        val state = userApiRepository.validateUserLogin(email, password)
        return if (state is LoginState.Success) {
            storeToken(state.token)
        } else {
            state
        }
    }

    /**
     * Stores the given token in the device.
     *
     * @param token a [Int] containing the user token
     *
     * @return the [LoginState] resulting in a successful validation
     */
    private fun storeToken(token: Int): LoginState {
        userStorageRepository.storeUserToken(token)
        return LoginState.Success(0)
    }
}
