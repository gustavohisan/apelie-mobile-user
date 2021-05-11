package com.gustavohisan.apelieuser.memory.injection

import com.gustavohisan.apelieuser.memory.datasource.intent.IntentDataSourceImpl
import com.gustavohisan.apelieuser.memory.loader.intent.LoginScreenIntentLoader
import com.gustavohisan.apelieuser.memory.loader.intent.MainScreenIntentLoader
import com.gustavohisan.apelieuser.repository.datasource.intent.IntentDataSource
import org.koin.dsl.module

/**
 * Module for dependency injection for the memory module.
 */
val memoryModule = module {

    // Data source
    factory<IntentDataSource> { IntentDataSourceImpl(get(), get()) }

    // Loader
    factory { MainScreenIntentLoader() }
    factory { LoginScreenIntentLoader() }
}
