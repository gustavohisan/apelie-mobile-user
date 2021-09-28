package com.gustavohisan.apelieuser.domain.injection

import com.gustavohisan.apelieuser.domain.usecase.login.ValidateLogin
import com.gustavohisan.apelieuser.domain.usecase.register.RegisterUser
import com.gustavohisan.apelieuser.domain.usecase.splash.CheckIfUserIsLoggedIn
import com.gustavohisan.apelieuser.domain.usecase.store.LoadMainScreenStoreList
import com.gustavohisan.apelieuser.domain.usecase.store.LoadStoreData
import org.koin.dsl.module

/**
 * Module for dependency injection for the domain module.
 */
val domainModule = module {

    // Use-case
    factory { ValidateLogin(get(), get()) }
    factory { CheckIfUserIsLoggedIn(get(), get()) }
    factory { RegisterUser(get()) }
    factory { LoadMainScreenStoreList(get()) }
    factory { LoadStoreData(get()) }
}
