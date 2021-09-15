package com.gustavohisan.apelieuser.feed.provider

import androidx.compose.runtime.Composable
import com.gustavohisan.apelieuser.feed.presentation.Feed
import com.gustavohisan.apelieuser.main.provider.FeedProvider

class FeedProviderImpl : FeedProvider {

    @Composable
    override fun FeedComposable(onStoreClicked: (Int) -> Unit) = Feed(onStoreClicked)
}
