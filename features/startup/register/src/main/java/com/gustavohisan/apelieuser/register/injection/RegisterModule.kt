package com.gustavohisan.apelieuser.register.injection

import com.gustavohisan.apelieuser.register.mapper.RegisterErrorTypeMapper
import com.gustavohisan.apelieuser.register.mapper.RegisterStateMapper
import com.gustavohisan.apelieuser.register.presentation.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Module for dependency injection for the Login module.
 */
val registerModule = module {

    // View model
    viewModel { RegisterViewModel(get(), get()) }

    // Mapper
    factory { RegisterStateMapper(get()) }
    factory { RegisterErrorTypeMapper() }
}
