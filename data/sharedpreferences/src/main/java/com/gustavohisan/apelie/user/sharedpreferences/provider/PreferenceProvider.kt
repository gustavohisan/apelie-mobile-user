package com.gustavohisan.apelie.user.sharedpreferences.provider

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import timber.log.Timber

/**
 * Provider used to provide the shared preferences to the application.
 *
 * @param context the application context
 */
internal class PreferenceProvider(private val context: Context) {

    /**
     * Stores the user token in a encrypted shared preferences.
     *
     * @param token a [Int] containing the token
     */
    fun storeUsersToken(token: Int) {
        Timber.d("storeUsersToken - token = $token")

        with(getSharedPreferences().edit()) {
            putInt(USER_TOKEN, token)
            apply()
        }
    }

    private fun getSharedPreferences() =
        EncryptedSharedPreferences.create(
            FILE_NAME,
            getMasterKey(),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    private fun getMasterKey() =
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    companion object {

        private const val FILE_NAME = "prefs"

        private const val USER_TOKEN = "USER_TOKEN"
    }
}
