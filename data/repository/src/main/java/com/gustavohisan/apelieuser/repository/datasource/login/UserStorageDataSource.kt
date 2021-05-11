package com.gustavohisan.apelieuser.repository.datasource.login

/**
 * User data source used to store the user data in a shared preferences.
 */
interface UserStorageDataSource {

    /**
     * Stores the user token in a encrypted shared preferences.
     *
     * @param token a [Int] containing the users token
     */
    fun storeUserToken(token: Int)

    /**
     * Return the user stored token.
     *
     * @return a [Int] containing the token
     */
    fun getUserStoredToken(): Int

    /**
     * Checks if there is a stored token in the shared preferences.
     *
     * @return if there is a stored token
     */
    fun hasStoredToken(): Boolean
}
