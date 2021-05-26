package com.gustavohisan.apelieuser.api.injection

import com.gustavohisan.apelieuser.api.datasource.store.StoreDataSourceImpl
import com.gustavohisan.apelieuser.api.datasource.user.UserApiDataSourceImpl
import com.gustavohisan.apelieuser.api.mapper.login.LoginErrorTypeMapper
import com.gustavohisan.apelieuser.api.mapper.login.LoginStateMapper
import com.gustavohisan.apelieuser.api.mapper.register.RegisterErrorTypeMapper
import com.gustavohisan.apelieuser.api.mapper.register.RegisterStateMapper
import com.gustavohisan.apelieuser.api.mapper.store.StoreMapper
import com.gustavohisan.apelieuser.api.mapper.store.StoreStateMapper
import com.gustavohisan.apelieuser.api.factory.ApiFactory
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import com.gustavohisan.apelieuser.repository.datasource.store.StoreDataSource
import org.koin.dsl.module

/**
 * Module for dependency injection for the api module.
 */
val apiModule = module {

    // Data source
    single<UserApiDataSource> { UserApiDataSourceImpl(get(), get(), get()) }
    single<StoreDataSource> { StoreDataSourceImpl(get(), get()) }

    // Mapper
    factory { LoginStateMapper(get()) }
    factory { LoginErrorTypeMapper() }
    factory { RegisterStateMapper(get()) }
    factory { RegisterErrorTypeMapper() }
    factory { StoreStateMapper(get()) }
    factory { StoreMapper() }

    // Provider
    single { ApiFactory() }
}
