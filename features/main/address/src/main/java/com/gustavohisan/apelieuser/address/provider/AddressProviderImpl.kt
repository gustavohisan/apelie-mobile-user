package com.gustavohisan.apelieuser.address.provider

import androidx.compose.runtime.Composable
import com.gustavohisan.apelieuser.address.presentation.Address
import com.gustavohisan.apelieuser.main.provider.AddressProvider

internal class AddressProviderImpl : AddressProvider {

    @Composable
    override fun AddressComposable(onBackClicked: () -> Unit, onDone: () -> Unit) =
        Address(onBackClicked = onBackClicked, onDone = onDone)
}
