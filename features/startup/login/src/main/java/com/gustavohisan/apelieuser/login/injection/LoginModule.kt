package com.gustavohisan.apelieuser.login.injection

import com.gustavohisan.apelieuser.login.mapper.LoginErrorTypeMapper
import com.gustavohisan.apelieuser.login.mapper.LoginStateMapper
import com.gustavohisan.apelieuser.login.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Module for dependency injection for the Login module.
 */
val loginModule = module {

    // ViewModel
    viewModel { LoginViewModel(get(), get(), get()) }

    // Mapper
    factory { LoginStateMapper(get()) }
    factory { LoginErrorTypeMapper() }
}
