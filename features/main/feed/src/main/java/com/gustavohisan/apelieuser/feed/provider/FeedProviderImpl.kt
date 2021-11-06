package com.gustavohisan.apelieuser.feed.provider

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.gustavohisan.apelieuser.feed.presentation.Feed
import com.gustavohisan.apelieuser.main.provider.FeedProvider

internal class FeedProviderImpl : FeedProvider {

    @ExperimentalPagerApi
    @Composable
    override fun FeedComposable(onStoreClicked: (Int) -> Unit) = Feed(onStoreClicked)
}
