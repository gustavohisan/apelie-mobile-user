package com.gustavohisan.apelieuser.main.provider

import androidx.compose.runtime.Composable

interface SearchProvider {

    @Composable
    fun SearchComposable(onStoreClicked: (Int) -> Unit)
}
