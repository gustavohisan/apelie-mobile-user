package com.gustavohisan.apelieuser.api.injection

import com.gustavohisan.apelieuser.api.datasource.user.UserApiDataSourceImpl
import com.gustavohisan.apelieuser.api.mapper.login.LoginErrorTypeMapper
import com.gustavohisan.apelieuser.api.mapper.login.LoginStateMapper
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import org.koin.dsl.module

/**
 * Module for dependency injection for the api module.
 */
val apiModule = module {
    // Data source
    factory<UserApiDataSource> { UserApiDataSourceImpl(get()) }

    // Mapper
    factory { LoginStateMapper(get()) }
    factory { LoginErrorTypeMapper() }
}
