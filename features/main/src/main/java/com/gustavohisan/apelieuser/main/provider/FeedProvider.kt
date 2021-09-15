package com.gustavohisan.apelieuser.main.provider

import androidx.compose.runtime.Composable

interface FeedProvider {

    @Composable
    fun FeedComposable(onStoreClicked: (Int) -> Unit)
}
