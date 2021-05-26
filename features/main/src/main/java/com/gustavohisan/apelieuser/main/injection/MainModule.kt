package com.gustavohisan.apelieuser.main.injection

import com.gustavohisan.apelieuser.main.mapper.StoreErrorTypeMapper
import com.gustavohisan.apelieuser.main.mapper.StoreMapper
import com.gustavohisan.apelieuser.main.mapper.StoreStateMapper
import com.gustavohisan.apelieuser.main.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Module for dependency injection for the main screen module.
 */
val mainModule = module {
    viewModel { HomeViewModel(get(), get()) }
    factory { StoreStateMapper(get(), get()) }
    factory { StoreErrorTypeMapper() }
    factory { StoreMapper() }
}
