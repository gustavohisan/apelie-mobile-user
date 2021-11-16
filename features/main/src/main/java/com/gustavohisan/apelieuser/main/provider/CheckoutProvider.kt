package com.gustavohisan.apelieuser.main.provider

import androidx.compose.runtime.Composable

interface CheckoutProvider {

    @Composable
    fun CheckoutComposable(
        onBackClicked: () -> Unit,
        onConfirmed: () -> Unit,
        onRegisterAddressClicked: () -> Unit
    )
}
