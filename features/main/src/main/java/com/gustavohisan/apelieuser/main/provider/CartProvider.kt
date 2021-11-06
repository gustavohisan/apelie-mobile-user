package com.gustavohisan.apelieuser.main.provider

import androidx.compose.runtime.Composable

interface CartProvider {

    @Composable
    fun CartComposable(onCheckoutSuccess: (Int) -> Unit)
}
