package com.gustavohisan.apelieuser.store.provider

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.gustavohisan.apelieuser.main.provider.StoreProvider
import com.gustavohisan.apelieuser.store.presentation.Store

internal class StoreProviderImpl : StoreProvider {

    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    @Composable
    override fun StoreComposable(
        storeId: Int,
        onProductClicked: (Int) -> Unit,
        onBackClicked: () -> Unit
    ) = Store(
        storeId = storeId,
        onProductClicked = onProductClicked,
        onBackClicked = onBackClicked
    )
}
