package com.gustavohisan.apelieuser.repository.injection

import com.gustavohisan.apelieuser.domain.repository.intent.IntentRepository
import com.gustavohisan.apelieuser.domain.repository.store.StoreRepository
import com.gustavohisan.apelieuser.domain.repository.user.UserApiRepository
import com.gustavohisan.apelieuser.domain.repository.user.UserStorageRepository
import com.gustavohisan.apelieuser.repository.mapper.login.LoginErrorTypeMapper
import com.gustavohisan.apelieuser.repository.mapper.login.LoginStateMapper
import com.gustavohisan.apelieuser.repository.mapper.register.RegisterErrorTypeMapper
import com.gustavohisan.apelieuser.repository.mapper.register.RegisterStateMapper
import com.gustavohisan.apelieuser.repository.mapper.store.*
import com.gustavohisan.apelieuser.repository.mapper.store.MainScreenStoreMapper
import com.gustavohisan.apelieuser.repository.mapper.store.MainScreenStoreStateMapper
import com.gustavohisan.apelieuser.repository.mapper.store.OwnerMapper
import com.gustavohisan.apelieuser.repository.mapper.store.StoreMapper
import com.gustavohisan.apelieuser.repository.mapper.store.StoreStateMapper
import com.gustavohisan.apelieuser.repository.repository.intent.IntentRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.store.StoreRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.user.UserApiRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.user.UserStorageRepositoryImpl
import org.koin.dsl.module

/**
 * Module for dependency injection for the repository module.
 */
val repositoryModule = module {

    // Repository
    factory<UserApiRepository> { UserApiRepositoryImpl(get(), get(), get()) }
    factory<UserStorageRepository> { UserStorageRepositoryImpl(get()) }
    factory<IntentRepository> { IntentRepositoryImpl(get()) }
    factory<StoreRepository> { StoreRepositoryImpl(get(), get(), get(), get(), get()) }

    // Mapper
    factory { LoginStateMapper(get()) }
    factory { LoginErrorTypeMapper() }
    factory { RegisterStateMapper(get()) }
    factory { RegisterErrorTypeMapper() }
    factory { MainScreenStoreStateMapper(get()) }
    factory { MainScreenStoreMapper() }
    factory { StoreMapper(get(), get()) }
    factory { StoreStateMapper(get()) }
    factory { OwnerMapper() }
    factory { ProductMapper() }
    factory { ProductStateMapper(get()) }
    factory { CategoryStateMapper() }
}
