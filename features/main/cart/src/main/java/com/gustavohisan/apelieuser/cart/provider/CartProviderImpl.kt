package com.gustavohisan.apelieuser.cart.provider

import androidx.compose.runtime.Composable
import com.gustavohisan.apelieuser.cart.presentation.Cart
import com.gustavohisan.apelieuser.main.provider.CartProvider

internal class CartProviderImpl : CartProvider {

    @Composable
    override fun CartComposable(onCheckoutSuccess: (Int) -> Unit) =
        Cart(onCheckoutSuccess = onCheckoutSuccess)
}
