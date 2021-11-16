package com.gustavohisan.apelieuser.profile.injection

import com.gustavohisan.apelieuser.main.provider.ProfileProvider
import com.gustavohisan.apelieuser.profile.presentation.ProfileViewModel
import com.gustavohisan.apelieuser.profile.provider.ProfileProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel { ProfileViewModel(get()) }
    factory<ProfileProvider> { ProfileProviderImpl() }
}
