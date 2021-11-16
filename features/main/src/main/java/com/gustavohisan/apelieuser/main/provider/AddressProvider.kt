package com.gustavohisan.apelieuser.main.provider

import androidx.compose.runtime.Composable

interface AddressProvider {

    @Composable
    fun AddressComposable(onBackClicked: () -> Unit, onDone: () -> Unit)
}
