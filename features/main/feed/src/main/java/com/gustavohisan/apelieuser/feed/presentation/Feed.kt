package com.gustavohisan.apelieuser.feed.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.gustavohisan.apelieuser.design.*
import com.gustavohisan.apelieuser.feed.R
import com.gustavohisan.apelieuser.feed.model.CategoryState
import com.gustavohisan.apelieuser.feed.model.MainScreenStore
import com.gustavohisan.apelieuser.feed.model.MainScreenStoreState
import com.gustavohisan.apelieuser.feed.provider.CategoryImageProvider
import com.gustavohisan.apelieuser.feedstore.FeedStore
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@ExperimentalPagerApi
@Composable
internal fun Feed(onStoreClicked: (Int) -> Unit) {
    FeedLoader(onStoreClicked)
}

@ExperimentalPagerApi
@Composable
private fun FeedLoader(
    onStoreClicked: (Int) -> Unit,
    viewModel: FeedViewModel = getViewModel()
) {
    val stateMainScreen by viewModel.storeState.observeAsState()
    if (stateMainScreen is MainScreenStoreState.Loading) {
        viewModel.getHomeStoreList()
    }
    val categoryState by viewModel.categoryState.observeAsState(CategoryState.Loading)
    if (categoryState is CategoryState.Loading) {
        viewModel.getCategories()
    }
    stateMainScreen?.let {
        FeedScaffold(
            onStoreClicked = onStoreClicked,
            stateMainScreen = it,
            categoryState = categoryState
        )
    }
}

@ExperimentalPagerApi
@Composable
private fun FeedScaffold(
    onStoreClicked: (Int) -> Unit,
    stateMainScreen: MainScreenStoreState,
    categoryState: CategoryState
) {
    val pagerState = rememberPagerState(0)
    val images =
        listOf(painterResource(R.mipmap.apelie_promo_1), painterResource(R.mipmap.apelie_promo_2))
    Scaffold {
        Crossfade(targetState = stateMainScreen) { state ->
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(mainBlue)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 15.dp)
                        ) {
                            Text(
                                text = "Bem-vindo ao ",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = "Apelie",
                                color = mainYellow,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .shadow(
                                    15.dp,
                                    shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
                                )
                                .clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                                .fillMaxWidth()
                                .background(Color.White)
                                .height(35.dp)
                        )
                    }
                }
                item {
                    HorizontalPager(
                        modifier = Modifier
                            .background(darkGrey)
                            .background(Color.White),
                        state = pagerState,
                        count = images.size
                    ) { page ->
                        Image(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .fillMaxWidth(),
                            painter = images[page],
                            contentDescription = null
                        )
                    }
                    HorizontalPagerIndicator(
                        modifier = Modifier.padding(top = 5.dp),
                        pagerState = pagerState
                    )
                }
                item {
                    when (categoryState) {
                        is CategoryState.Loading -> {
                        }
                        is CategoryState.Error -> {
                        }
                        is CategoryState.Success -> {
                            LazyRow(modifier = Modifier.padding(vertical = 20.dp)) {
                                items(categoryState.categories) { category ->
                                    CategoryFilter(category = category)
                                }
                            }
                        }
                    }
                }
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
                        text = "Lojas em destaque",
                        color = textGrey,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                when (state) {
                    is MainScreenStoreState.Loading -> {
                        item { StorePlaceholder() }
                        item { StorePlaceholder() }
                        item { StorePlaceholder() }
                        item { StorePlaceholder() }
                    }
                    is MainScreenStoreState.Success -> {
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
                                rating = store.rating,
                                productImages = store.products
                            )
                        }
                    }
                    is MainScreenStoreState.Error -> {
                        item {
                            Text(
                                text = "Error",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
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

@Composable
private fun CategoryFilter(category: String, categoryImageProvider: CategoryImageProvider = get()) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(10.dp))
                .size(width = 100.dp, height = 60.dp),
            painter = painterResource(id = categoryImageProvider.getImageFromCategory(category)),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = category,
            color = textGrey,
            maxLines = 1,
            fontSize = 12.sp
        )
    }
}
