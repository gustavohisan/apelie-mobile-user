package com.gustavohisan.apelie.user.sharedpreferences.datasource.user

import com.gustavohisan.apelie.user.sharedpreferences.provider.PreferenceProvider
import com.gustavohisan.apelieuser.repository.datasource.login.UserStorageDataSource
import timber.log.Timber

/**
 * Implementation of [UserStorageDataSource].
 *
 * @param preferenceProvider provider used to provide the shared preferences of the project
 */
internal class UserStorageDataSourceImpl(
    private val preferenceProvider: PreferenceProvider
) : UserStorageDataSource {

    override fun storeUserToken(token: String) {
        Timber.d("storeUsersToken - token = $token")
        with(preferenceProvider.getSharedPreferences().edit()) {
            putString(USER_TOKEN, token)
            apply()
        }
    }

    override fun getUserStoredToken(): String {
        val token = preferenceProvider.getSharedPreferences().getString(USER_TOKEN, DEF_VALUE_TOKEN)
        Timber.d("getStoredToken = $token")
        return token.orEmpty()
    }

    override fun hasStoredToken(): Boolean {
        val hasStoredToken = preferenceProvider.getSharedPreferences().contains(USER_TOKEN)
        Timber.d("hasStoredToken = $hasStoredToken")
        return hasStoredToken
    }

    override fun removeUserToken() = with(preferenceProvider.getSharedPreferences().edit()) {
        remove(USER_TOKEN)
        commit()
    }


    companion object {

        private const val USER_TOKEN = "USER_TOKEN"

        private const val DEF_VALUE_TOKEN = ""
    }
}
