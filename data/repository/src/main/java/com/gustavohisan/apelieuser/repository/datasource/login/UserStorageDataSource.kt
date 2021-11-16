package com.gustavohisan.apelieuser.repository.datasource.login

/**
 * User data source used to store the user data in a shared preferences.
 */
interface UserStorageDataSource {

    /**
     * Stores the user token in a encrypted shared preferences.
     *
     * @param token a [String] containing the users token
     */
    fun storeUserToken(token: String)

    /**
     * Return the user stored token.
     *
     * @return a [String] containing the token
     */
    fun getUserStoredToken(): String

    /**
     * Checks if there is a stored token in the shared preferences.
     *
     * @return if there is a stored token
     */
    fun hasStoredToken(): Boolean

    fun removeUserToken(): Boolean
}
