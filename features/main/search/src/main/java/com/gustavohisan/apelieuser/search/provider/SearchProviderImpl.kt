package com.gustavohisan.apelieuser.search.provider

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.gustavohisan.apelieuser.main.provider.SearchProvider
import com.gustavohisan.apelieuser.search.presentation.Search

internal class SearchProviderImpl : SearchProvider {

    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    @ExperimentalComposeUiApi
    @Composable
    override fun SearchComposable(onStoreClicked: (Int) -> Unit, paddings: PaddingValues) =
        Search(onStoreClicked = onStoreClicked, paddings = paddings)
}
