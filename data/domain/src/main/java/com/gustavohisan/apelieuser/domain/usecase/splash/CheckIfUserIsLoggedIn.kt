package com.gustavohisan.apelieuser.domain.usecase.splash

import com.gustavohisan.apelieuser.domain.repository.user.UserApiRepository
import com.gustavohisan.apelieuser.domain.repository.user.UserStorageRepository

/**
 * Use-case used to check if there is a valid logged account in the device.
 *
 * @param userApiRepository repository used to check for the token validation
 * @param userStorageRepository repository used to retrieve if there is a token stored
 */
class CheckIfUserIsLoggedIn(
    private val userApiRepository: UserApiRepository,
    private val userStorageRepository: UserStorageRepository
) {

    /**
     * Checks for an available token stored in the shared preferences, if found checks whether it
     * is a valid token.
     *
     * @return if there is a valid token stored
     */
    operator fun invoke(): Boolean =
        if (userStorageRepository.hasStoredUser()) {
            validateUserToken()
        } else {
            false
        }

    /**
     * Checks if the stored token still is valid in the API, if so caches the token.
     *
     * @return if the stored token is valid
     */
    private fun validateUserToken(): Boolean {
        val token = userStorageRepository.getStoredUserToken()
        if (userApiRepository.validateUserToken(token)) {
            // TODO cache token
            return true
        } else {
            return false
        }
    }
}
