package com.gustavohisan.apelieuser.profile.injection

import com.gustavohisan.apelieuser.main.provider.ProfileProvider
import com.gustavohisan.apelieuser.profile.provider.ProfileProviderImpl
import org.koin.dsl.module

val profileModule = module {
    factory<ProfileProvider> { ProfileProviderImpl() }
}
