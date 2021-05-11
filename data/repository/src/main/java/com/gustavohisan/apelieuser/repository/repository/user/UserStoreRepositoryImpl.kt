package com.gustavohisan.apelieuser.repository.repository.user

import com.gustavohisan.apelieuser.domain.repository.user.UserStorageRepository
import com.gustavohisan.apelieuser.repository.datasource.login.UserStorageDataSource

/**
 * Implementation of [UserStorageRepository].
 *
 * @param userStoreDataSource data source used to store the user information
 */
internal class UserStoreRepositoryImpl(
    private val userStoreDataSource: UserStorageDataSource
) : UserStorageRepository {

    override fun storeUserToken(token: Int) =
        userStoreDataSource.storeUserToken(token)

    override fun getStoredUserToken(): Int =
        userStoreDataSource.getUserStoredToken()

    override fun hasStoredUser(): Boolean =
        userStoreDataSource.hasStoredToken()
}
