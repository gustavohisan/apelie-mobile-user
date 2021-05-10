package com.gustavohisan.apelieuser.repository.injection

import com.gustavohisan.apelieuser.domain.repository.intent.IntentRepository
import com.gustavohisan.apelieuser.domain.repository.login.UserApiRepository
import com.gustavohisan.apelieuser.domain.repository.login.UserStorageRepository
import com.gustavohisan.apelieuser.repository.mapper.login.LoginErrorTypeMapper
import com.gustavohisan.apelieuser.repository.mapper.login.LoginStateMapper
import com.gustavohisan.apelieuser.repository.repository.intent.IntentRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.login.UserApiRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.login.UserStoreRepositoryImpl
import org.koin.dsl.module

/**
 * Module for dependency injection for the repository module.
 */
val repositoryModule = module {

    // Repository
    factory<UserApiRepository> { UserApiRepositoryImpl(get(), get()) }
    factory<UserStorageRepository> { UserStoreRepositoryImpl(get()) }
    factory<IntentRepository> { IntentRepositoryImpl(get()) }

    // Mapper
    factory { LoginStateMapper(get()) }
    factory { LoginErrorTypeMapper() }
}
