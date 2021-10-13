package com.gustavohisan.apelieuser.feed.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.gustavohisan.apelieuser.design.darkGrey
import com.gustavohisan.apelieuser.design.ratingYellow
import com.gustavohisan.apelieuser.feed.model.MainScreenStore
import com.gustavohisan.apelieuser.feed.model.MainScreenStoreState
import com.gustavohisan.apelieuser.feedstore.FeedStore
import org.koin.androidx.compose.getViewModel

@Composable
internal fun Feed(onStoreClicked: (Int) -> Unit) {
    FeedLoader(onStoreClicked)
}

@Composable
private fun FeedLoader(
    onStoreClicked: (Int) -> Unit,
    viewModel: FeedViewModel = getViewModel()
) {
    val stateMainScreen by viewModel.storeState.observeAsState()
    if (stateMainScreen is MainScreenStoreState.Loading) {
        viewModel.getHomeStoreList()
    }
    stateMainScreen?.let { FeedScaffold(onStoreClicked = onStoreClicked, stateMainScreen = it) }
}

@Composable
private fun FeedScaffold(onStoreClicked: (Int) -> Unit, stateMainScreen: MainScreenStoreState) {
    Scaffold {
        Crossfade(targetState = stateMainScreen) { state ->
            when (state) {
                is MainScreenStoreState.Loading -> {
                    Column {
                        StorePlaceholder()
                        StorePlaceholder()
                        StorePlaceholder()
                        StorePlaceholder()
                    }
                }
                is MainScreenStoreState.Success -> {
                    LazyColumn {
                        items(state.mainScreenStoreList) { store ->
                            FeedStore(
                                onStoreClicked = onStoreClicked,
                                storeId = store.storeId,
                                name = store.name,
                                categories = store.category,
                                bannerUrl = store.bannerUrl,
                                storeUrl = store.logoUrl,
                                state = store.state,
                                city = store.city,
                                rating = store.rating
                            )
                        }
                    }
                }
                is MainScreenStoreState.Error -> {
                    Text(
                        text = "Error",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun StorePlaceholder() {
    Column(
        Modifier.placeholder(
            visible = true,
            color = Color.LightGray,
            highlight = PlaceholderHighlight.fade(Color.White)
        )
    ) {
        Row {

        }
    }
}
