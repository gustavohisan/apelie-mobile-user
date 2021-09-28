package com.gustavohisan.apelieuser.main.provider

import androidx.compose.runtime.Composable

interface StoreProvider {

    @Composable
    fun StoreComposable(storeId: Int, onProductClicked: (Int) -> Unit, onBackClicked: () -> Unit)
}
