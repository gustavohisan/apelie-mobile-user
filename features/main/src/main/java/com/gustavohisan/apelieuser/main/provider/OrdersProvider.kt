package com.gustavohisan.apelieuser.main.provider

import androidx.compose.runtime.Composable

interface OrdersProvider {

    @Composable
    fun OrdersComposable(onOrderClicked: (Int) -> Unit)
}
