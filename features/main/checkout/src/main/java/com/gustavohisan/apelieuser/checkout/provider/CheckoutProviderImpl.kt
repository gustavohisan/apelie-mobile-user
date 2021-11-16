package com.gustavohisan.apelieuser.checkout.provider

import androidx.compose.runtime.Composable
import com.gustavohisan.apelieuser.checkout.presentation.Checkout
import com.gustavohisan.apelieuser.main.provider.CheckoutProvider

internal class CheckoutProviderImpl : CheckoutProvider {

    @Composable
    override fun CheckoutComposable(
        onBackClicked: () -> Unit,
        onConfirmed: () -> Unit,
        onRegisterAddressClicked: () -> Unit
    ) = Checkout(onBackClicked, onConfirmed, onRegisterAddressClicked)
}
