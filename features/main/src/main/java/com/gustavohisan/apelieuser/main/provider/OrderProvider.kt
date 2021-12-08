package com.gustavohisan.apelieuser.main.provider

import androidx.compose.runtime.Composable

interface OrderProvider {

    @Composable
    fun OrderComposable(orderId: Int, onBackClicked: () -> Unit)
}
