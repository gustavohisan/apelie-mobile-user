package com.gustavohisan.apelieuser.splash.injection

import com.gustavohisan.apelieuser.splash.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Module for dependency injection for the Splash module.
 */
val splashModule = module {

    // View model
    viewModel { SplashViewModel(get(), get(), get()) }
}
