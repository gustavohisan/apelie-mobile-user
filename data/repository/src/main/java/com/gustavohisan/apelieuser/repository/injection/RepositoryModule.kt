package com.gustavohisan.apelieuser.repository.injection

import com.gustavohisan.apelieuser.domain.repository.intent.IntentRepository
import com.gustavohisan.apelieuser.domain.repository.user.UserApiRepository
import com.gustavohisan.apelieuser.domain.repository.user.UserStorageRepository
import com.gustavohisan.apelieuser.repository.mapper.login.LoginErrorTypeMapper
import com.gustavohisan.apelieuser.repository.mapper.login.LoginStateMapper
import com.gustavohisan.apelieuser.repository.mapper.register.RegisterErrorTypeMapper
import com.gustavohisan.apelieuser.repository.mapper.register.RegisterStateMapper
import com.gustavohisan.apelieuser.repository.repository.intent.IntentRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.user.UserApiRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.user.UserStoreRepositoryImpl
import org.koin.dsl.module

/**
 * Module for dependency injection for the repository module.
 */
val repositoryModule = module {

    // Repository
    factory<UserApiRepository> { UserApiRepositoryImpl(get(), get(), get()) }
    factory<UserStorageRepository> { UserStoreRepositoryImpl(get()) }
    factory<IntentRepository> { IntentRepositoryImpl(get()) }

    // Mapper
    factory { LoginStateMapper(get()) }
    factory { LoginErrorTypeMapper() }
    factory { RegisterStateMapper(get()) }
    factory { RegisterErrorTypeMapper() }
}
