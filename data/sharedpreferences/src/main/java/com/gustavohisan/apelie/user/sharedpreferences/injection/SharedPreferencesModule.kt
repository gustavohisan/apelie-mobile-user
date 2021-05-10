package com.gustavohisan.apelie.user.sharedpreferences.injection

import com.gustavohisan.apelie.user.sharedpreferences.datasource.login.UserStorageDataSourceImpl
import com.gustavohisan.apelie.user.sharedpreferences.provider.PreferenceProvider
import com.gustavohisan.apelieuser.repository.datasource.login.UserStorageDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Module for dependency injection for the shared preferences module.
 */
val sharedPreferencesModule = module {

    // Data source
    factory<UserStorageDataSource> { UserStorageDataSourceImpl(get()) }

    // Provider
    factory { PreferenceProvider(androidContext()) }
}
