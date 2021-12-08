package com.gustavohisan.apelieuser.orders.provider

import androidx.compose.runtime.Composable
import com.gustavohisan.apelieuser.main.provider.OrderProvider
import com.gustavohisan.apelieuser.orders.presentation.Order

internal class OrderProviderImpl : OrderProvider {

    @Composable
    override fun OrderComposable(orderId: Int, onBackClicked: () -> Unit) =
        Order(orderId = orderId, onBackClicked = onBackClicked)
}
