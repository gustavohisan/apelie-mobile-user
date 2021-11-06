package com.gustavohisan.apelieuser.profile.provider

import androidx.compose.runtime.Composable
import com.gustavohisan.apelieuser.main.provider.ProfileProvider
import com.gustavohisan.apelieuser.profile.presentation.Profile

internal class ProfileProviderImpl: ProfileProvider {

    @Composable
    override fun ProfileComposable() = Profile()
}
