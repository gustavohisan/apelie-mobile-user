package com.gustavohisan.apelieuser.feed.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.gustavohisan.apelieuser.design.darkGrey
import com.gustavohisan.apelieuser.design.ratingYellow
import com.gustavohisan.apelieuser.feed.model.Store
import com.gustavohisan.apelieuser.feed.model.StoreState
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
    val state: StoreState by viewModel.storeState.observeAsState(StoreState.Loading)
    viewModel.getHomeStoreList()
    FeedScaffold(onStoreClicked = onStoreClicked, state = state)
}

@Composable
private fun FeedScaffold(onStoreClicked: (Int) -> Unit, state: StoreState) {
    Scaffold {
        Crossfade(targetState = state) { state ->
            when (state) {
                is StoreState.Loading -> {
                    Column {
                        StorePlaceholder()
                        StorePlaceholder()
                        StorePlaceholder()
                        StorePlaceholder()
                    }
                }
                is StoreState.Success -> {
                    LazyColumn {
                        items(state.storeList) { store ->
                            Store(onStoreClicked = onStoreClicked, store = store)
                        }
                    }
                }
                is StoreState.Error -> {
                    Text(
                        text = "Error",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun Store(onStoreClicked: (Int) -> Unit, store: Store) {
    val bannerImage = rememberImagePainter(data = store.bannerUrl)
    val storeImage = rememberImagePainter(
        data = store.logoUrl,
    )
    Box(
        Modifier
            .clickable(onClick = { onStoreClicked(store.storeId) })
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth()
            .height(200.dp)
            .border(0.dp, Color.White, shape = RoundedCornerShape(10.dp))
            .shadow(1.dp, shape = RoundedCornerShape(10.dp))
    ) {
        Image(
            painter = bannerImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .placeholder(
                    visible = (bannerImage.state is ImagePainter.State.Success).not(),
                    color = Color.LightGray,
                    highlight = PlaceholderHighlight.fade(Color.White)
                )
        )
        Row(modifier = Modifier.padding(top = 50.dp, start = 10.dp)) {
            Image(
                painter = storeImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .border(width = 4.dp, color = Color.White, shape = RoundedCornerShape(10.dp))
                    .padding(4.dp)
                    .size(100.dp)
                    .placeholder(
                        visible = (storeImage.state is ImagePainter.State.Success).not(),
                        color = Color.LightGray,
                        highlight = PlaceholderHighlight.fade(Color.White)
                    )
            )
            Row(
                modifier = Modifier.padding(top = 34.dp, start = 6.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1F)) {
                    Text(
                        text = store.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = store.category.joinToString(separator = ", ", limit = 3),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = darkGrey,
                        fontSize = 12.sp,
                    )
                    Text(
                        text = "${store.city}, ${store.state}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 12.sp,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = ratingYellow,
                    )
                    Text(
                        text = store.rating.toString(),
                        maxLines = 1,
                        overflow = TextOverflow.Visible,
                        color = ratingYellow,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
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
