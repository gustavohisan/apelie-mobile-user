package com.gustavohisan.apelieuser.domain.repository.user

/**
 * User repository used to store the user data.
 */
interface UserStorageRepository {

    /**
     * Stores the user token.
     *
     * @param token a [String] containing the users token
     */
    fun storeUserToken(token: String)

    /**
     * Return the user stored token.
     *
     * @return a [String] containing the token
     */
    fun getStoredUserToken(): String

    /**
     * Checks if there is a stored token in the device.
     *
     * @return if there is a stored token
     */
    fun hasStoredUser(): Boolean
}
