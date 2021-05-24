package com.gustavohisan.apelieuser.repository.repository.user

import com.gustavohisan.apelieuser.domain.repository.user.UserStorageRepository
import com.gustavohisan.apelieuser.repository.datasource.login.UserStorageDataSource

/**
 * Implementation of [UserStorageRepository].
 *
 * @param userStorageDataSource data source used to store the user information
 */
internal class UserStorageRepositoryImpl(
    private val userStorageDataSource: UserStorageDataSource
) : UserStorageRepository {

    override fun storeUserToken(token: String) =
        userStorageDataSource.storeUserToken(token)

    override fun getStoredUserToken(): String =
        userStorageDataSource.getUserStoredToken()

    override fun hasStoredUser(): Boolean =
        userStorageDataSource.hasStoredToken()
}
