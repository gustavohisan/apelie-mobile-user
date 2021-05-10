package com.gustavohisan.apelieuser.repository.repository.login

import com.gustavohisan.apelieuser.domain.repository.login.UserStorageRepository
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
}
