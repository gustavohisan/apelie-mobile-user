package com.gustavohisan.apelieuser.domain.repository.login

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
}
