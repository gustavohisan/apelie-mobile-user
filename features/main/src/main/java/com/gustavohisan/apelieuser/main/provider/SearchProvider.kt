package com.gustavohisan.apelieuser.main.provider

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

interface SearchProvider {

    @Composable
    fun SearchComposable(onStoreClicked: (Int) -> Unit, padding: PaddingValues)
}
