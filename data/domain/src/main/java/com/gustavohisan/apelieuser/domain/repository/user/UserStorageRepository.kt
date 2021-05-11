package com.gustavohisan.apelieuser.domain.repository.user

/**
 * User repository used to store the user data.
 */
interface UserStorageRepository {

    /**
     * Stores the user token.
     *
     * @param token a [Int] containing the users token
     */
    fun storeUserToken(token: Int)

    /**
     * Return the user stored token.
     *
     * @return a [Int] containing the token
     */
    fun getStoredUserToken(): Int

    /**
     * Checks if there is a stored token in the device.
     *
     * @return if there is a stored token
     */
    fun hasStoredUser(): Boolean
}
