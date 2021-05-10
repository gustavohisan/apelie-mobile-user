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
}
