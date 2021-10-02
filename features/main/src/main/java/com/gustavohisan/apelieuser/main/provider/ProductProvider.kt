package com.gustavohisan.apelieuser.main.provider

import androidx.compose.runtime.Composable

interface ProductProvider {

    @Composable
    fun ProductComposable(productId: Int, onAddToCardSuccess: () -> Unit, onBackClicked: () -> Unit)
}
