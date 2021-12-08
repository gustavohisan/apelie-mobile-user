package com.gustavohisan.apelieuser.orders.provider

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.gustavohisan.apelieuser.main.provider.OrdersProvider
import com.gustavohisan.apelieuser.orders.presentation.Orders

internal class OrdersProviderImpl : OrdersProvider {

    @ExperimentalFoundationApi
    @Composable
    override fun OrdersComposable(onOrderClicked: (Int) -> Unit) =
        Orders(onOrderClicked = onOrderClicked)
}
