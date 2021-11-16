package com.gustavohisan.apelieuser.main.provider

import androidx.compose.runtime.Composable

interface OrderProvider {

    @Composable
    fun OrderPComposable(orderId: Int, onBackClicked: () -> Unit)
}
