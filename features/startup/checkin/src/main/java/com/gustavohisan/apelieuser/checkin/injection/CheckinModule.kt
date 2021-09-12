package com.gustavohisan.apelieuser.checkin.injection

import com.gustavohisan.apelieuser.checkin.presentation.CheckinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Module for dependency injection for the check-in module.
 */
val checkinModule = module {

    // View model
    viewModel { CheckinViewModel(get()) }
}
