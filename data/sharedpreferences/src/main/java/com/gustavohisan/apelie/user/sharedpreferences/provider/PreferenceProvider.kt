package com.gustavohisan.apelie.user.sharedpreferences.provider

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

/**
 * Provider used to provide the shared preferences to the application.
 *
 * @param context the application context
 */
internal class PreferenceProvider(private val context: Context) {

    /**
     * Retrieves the instance of the shared preferences.
     *
     * @return a [EncryptedSharedPreferences] of the device
     */
    fun getSharedPreferences() =
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
    }
}
