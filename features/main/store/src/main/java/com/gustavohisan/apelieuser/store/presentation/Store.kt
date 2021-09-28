package com.gustavohisan.apelieuser.store.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.gustavohisan.apelieuser.design.*
import com.gustavohisan.apelieuser.store.model.*
import com.gustavohisan.apelieuser.store.model.Product
import com.gustavohisan.apelieuser.store.model.Store
import com.gustavohisan.apelieuser.store.model.StoreItem
import com.gustavohisan.apelieuser.store.model.StoreState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
internal fun Store(
    storeId: Int,
    onProductClicked: (Int) -> Unit,
    onBackClicked: () -> Unit
) {
    StoreLoader(
        storeId = storeId,
        onProductClicked = onProductClicked,
        onBackClicked = onBackClicked
    )
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun StoreLoader(
    storeId: Int,
    onProductClicked: (Int) -> Unit,
    onBackClicked: () -> Unit,
    viewModel: StoreViewModel = getViewModel()
) {
    viewModel.getStoreData(storeId = storeId)
    val storeState: StoreState by viewModel.storeDataState.observeAsState(StoreState.Loading)
    StoreScaffold(storeState, onProductClicked, onBackClicked, viewModel)
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun StoreScaffold(
    state: StoreState,
    onProductClicked: (Int) -> Unit,
    onBackClicked: () -> Unit,
    viewModel: StoreViewModel
) {
    val (selectedIndex, setSelectedIndex) = remember { mutableStateOf(0) }
    val categoriesIndex = remember { mutableStateListOf<Int>() }
    Scaffold(modifier = Modifier.fillMaxSize()) {
        val listState = rememberLazyListState(0)
        val coroutineScope = rememberCoroutineScope()
        when (state) {
            is StoreState.Loading -> StorePlaceholder()
            is StoreState.Error -> Timber.d("StoreScaffold Error - ${state.errorType}")
            is StoreState.Success -> {
                StoreBody(
                    store = state.store,
                    selectedIndex = selectedIndex,
                    setSelectedIndex = setSelectedIndex,
                    onProductClicked = onProductClicked,
                    listState = listState,
                    viewModel = viewModel,
                    categoriesIndex = categoriesIndex,
                    coroutineScope = coroutineScope
                )
            }

        }
        BackButton(onBackClicked = onBackClicked)
    }
}

@Composable
private fun StorePlaceholder() {

}

@Composable
private fun StoreHeader(
    store: Store
) {
    val bannerImage = rememberImagePainter(data = store.bannerUrl)
    val storeImage = rememberImagePainter(data = store.logoUrl)
    Column {
        Box {
            Image(
                painter = bannerImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .placeholder(
                        visible = (bannerImage.state is ImagePainter.State.Success).not(),
                        color = Color.LightGray,
                        highlight = PlaceholderHighlight.fade(Color.White)
                    )
            )
            Column(modifier = Modifier.padding(top = 100.dp, start = 20.dp, end = 20.dp)) {
                Image(
                    painter = storeImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .border(
                            width = 3.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(3.dp)
                        .size(100.dp)
                        .placeholder(
                            visible = (storeImage.state is ImagePainter.State.Success).not(),
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight.fade(Color.White)
                        )
                )
                Row(
                    modifier = Modifier.padding(top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1F),
                        text = store.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
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
}

@ExperimentalAnimationApi
@Composable
private fun StoreTabRow(
    store: Store,
    tabs: List<String>,
    selectedIndex: Int,
    setSelectedIndex: (Int) -> Unit,
    showTabRowText: Boolean,
    coroutineScope: CoroutineScope,
    listState: LazyListState,
    categoriesIndex: MutableList<Int>
) {
    Column(modifier = Modifier.background(Color.White)) {
        Crossfade(
            showTabRowText,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .height(50.dp)
        ) { isShowing ->
            if (isShowing) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        text = store.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            } else {
                Column {
                    Text(
                        text = "${store.city}, ${store.state}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 12.sp,
                    )
                    Text(
                        text = store.category.joinToString(separator = ", ", limit = 3),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = darkGrey,
                        fontSize = 12.sp,
                    )
                }
            }
        }
        ScrollableTabRow(
            backgroundColor = Color.White,
            contentColor = mainBlue,
            selectedTabIndex = selectedIndex,
        ) {
            tabs.forEachIndexed { index, tabText ->
                Tab(
                    modifier = Modifier.height(50.dp),
                    selected = index == selectedIndex,
                    selectedContentColor = mainBlue,
                    unselectedContentColor = mainGrey,
                    onClick = {
                        setSelectedIndex(index)
                        coroutineScope.launch {
                            listState.animateScrollToItem(categoriesIndex[index])
                        }
                    }) {
                    Text(text = tabText)
                }
            }
        }
    }
}

@Composable
private fun BackButton(
    onBackClicked: () -> Unit,
) {
    IconButton(
        onClick = onBackClicked,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Color.Black.copy(alpha = 0.25f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            tint = Color.White,
            contentDescription = "Back"
        )
    }
}

@Composable
private fun ChatButton() {
    IconButton(
        onClick = {},
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Color.Black.copy(alpha = 0.25f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Filled.Chat,
            tint = Color.White,
            contentDescription = "Back"
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun StoreBody(
    store: Store,
    selectedIndex: Int,
    setSelectedIndex: (Int) -> Unit,
    onProductClicked: (Int) -> Unit,
    listState: LazyListState,
    viewModel: StoreViewModel,
    categoriesIndex: MutableList<Int>,
    coroutineScope: CoroutineScope
) {
    val showTabRowText by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }
    val setIndexOnScroll by remember { derivedStateOf { categoriesIndex.isEmpty() } }
    if (setIndexOnScroll.not()) {
        when {
            listState.firstVisibleItemIndex in categoriesIndex -> {
                setSelectedIndex(categoriesIndex.indexOf(listState.firstVisibleItemIndex))
            }
            listState.firstVisibleItemIndex < 2 -> setSelectedIndex(0)
            listState.firstVisibleItemIndex < categoriesIndex[selectedIndex] -> {
                setSelectedIndex(selectedIndex - 1)
            }
        }
    }
    val products = viewModel.mapCategoriesToUi(store.products)
    val tabs = store.products.map { it.category }
    LazyColumn(modifier = Modifier.fillMaxWidth(), state = listState) {
        item {
            StoreHeader(store = store)
        }
        stickyHeader {
            StoreTabRow(
                store = store,
                tabs = tabs,
                selectedIndex = selectedIndex,
                setSelectedIndex = setSelectedIndex,
                showTabRowText = showTabRowText,
                coroutineScope = coroutineScope,
                listState = listState,
                categoriesIndex = categoriesIndex
            )
        }
        products.forEachIndexed { index, storeItem ->
            when (storeItem) {
                is StoreItem.Category -> {
                    categoriesIndex.add(index + 2)
                    item {
                        CategoryTitle(name = storeItem.name)
                    }
                }
                is StoreItem.ProductItem -> {
                    item {
                        Product(product = storeItem.product, onProductClicked = onProductClicked)
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryTitle(name: String) {
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = name,
            color = textGrey,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Divider(modifier = Modifier.fillMaxWidth())
}

@Composable
private fun Product(product: Product, onProductClicked: (Int) -> Unit) {
    val productImage = rememberImagePainter(data = product.images.first())
    Box(
        Modifier
            .padding(vertical = 15.dp, horizontal = 15.dp)
            .fillMaxWidth()
            .height(200.dp)
            .border(0.dp, Color.White, shape = RoundedCornerShape(10.dp))
            .shadow(1.dp, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = { onProductClicked(product.id) })
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 15.dp, start = 15.dp),
            text = "R$ ${"%.2f".format(product.price)}",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = moneyGreen,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight()
                    .padding(15.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 15.dp),
                    text = product.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = product.description,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    color = darkGrey,
                    fontSize = 12.sp,
                )
            }
            Image(
                painter = productImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .placeholder(
                        visible = (productImage.state is ImagePainter.State.Success).not(),
                        color = Color.LightGray,
                        highlight = PlaceholderHighlight.fade(Color.White)
                    )
            )
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun StorePreview() {
    StoreScaffold(
        state = StoreState.Loading,
        onBackClicked = {},
        onProductClicked = {},
        viewModel = getViewModel()
    )
}
