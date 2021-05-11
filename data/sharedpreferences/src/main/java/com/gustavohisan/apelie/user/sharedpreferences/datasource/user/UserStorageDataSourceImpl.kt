package com.gustavohisan.apelie.user.sharedpreferences.datasource.user

import com.gustavohisan.apelie.user.sharedpreferences.provider.PreferenceProvider
import com.gustavohisan.apelieuser.repository.datasource.login.UserStorageDataSource

/**
 * Implementation of [UserStorageDataSource].
 *
 * @param preferenceProvider provider used to provide the shared preferences of the project
 */
internal class UserStorageDataSourceImpl(
    private val preferenceProvider: PreferenceProvider
) : UserStorageDataSource {

    override fun storeUserToken(token: Int) {
        preferenceProvider.storeUsersToken(token)
    }

    override fun getUserStoredToken(): Int =
        preferenceProvider.getStoredToken()

    override fun hasStoredToken(): Boolean =
        preferenceProvider.hasStoredToken()
}
