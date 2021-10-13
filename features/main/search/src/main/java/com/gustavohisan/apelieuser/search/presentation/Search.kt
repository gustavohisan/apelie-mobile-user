package com.gustavohisan.apelieuser.search.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.gustavohisan.apelieuser.design.*
import com.gustavohisan.apelieuser.feedstore.FeedStore
import com.gustavohisan.apelieuser.search.model.CategoryState
import com.gustavohisan.apelieuser.search.model.Filter
import com.gustavohisan.apelieuser.search.model.SearchStoresState
import org.koin.androidx.compose.getViewModel

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun Search(onStoreClicked: (Int) -> Unit, paddings: PaddingValues) {
    SearchLoader(onStoreClicked = onStoreClicked, paddings = paddings)
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
private fun SearchLoader(
    onStoreClicked: (Int) -> Unit,
    paddings: PaddingValues,
    viewModel: SearchViewModel = getViewModel()
) {
    val searchState by viewModel.searchState.observeAsState()
    val categoryState by viewModel.categoryState.observeAsState(CategoryState.Loading)
    if (categoryState is CategoryState.Loading) {
        viewModel.getCategories()
    }
    searchState?.let {
        SearchScaffold(
            onStoreClicked = onStoreClicked,
            paddings = paddings,
            searchState = it,
            categoryState = categoryState,
            viewModel = viewModel
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
private fun SearchScaffold(
    onStoreClicked: (Int) -> Unit,
    paddings: PaddingValues,
    searchState: SearchStoresState,
    categoryState: CategoryState,
    viewModel: SearchViewModel
) {
    val (query, setQuery) = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    var visible by remember { mutableStateOf(false) }
    val filterList = remember { mutableListOf<Filter>() }
    Scaffold(modifier = Modifier.padding(paddings)) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    visible = visible.not()
                }) {
                    Icon(imageVector = Icons.Filled.FilterList, "Filtrar")
                }
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1F)
                        .padding(vertical = 10.dp),
                    value = query,
                    singleLine = true,
                    onValueChange = { value -> setQuery(value) },
                    placeholder = { Text("Pesquisar") },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            viewModel.searchStores(query, filterList)
                        }
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = textGrey,
                        cursorColor = mainGrey,
                        backgroundColor = backgroundGrey,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
                TextButton(
                    onClick = {
                        setQuery("")
                        viewModel.setNoQueryState()
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.Transparent)
                ) {
                    Text(
                        text = "Cancelar",
                        textAlign = TextAlign.Center,
                        color = textGrey
                    )
                }
            }
            AnimatedVisibility(visible = visible) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = backgroundGrey)
                ) {
                    when (categoryState) {
                        is CategoryState.Loading -> {
                        }
                        is CategoryState.Error -> {
                        }
                        is CategoryState.Success -> {
                            filterList.clear()
                            filterList.addAll(categoryState.categories)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.padding(start = 20.dp),
                                    text = "Categorias",
                                    color = textGrey,
                                    fontWeight = FontWeight.Bold
                                )
                                TextButton(
                                    onClick = {
                                        filterList.forEach { it.enabled.value = false }
                                        viewModel.searchStores(query, filterList)
                                    },
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .height(40.dp),
                                    colors = ButtonDefaults.textButtonColors(contentColor = Color.Transparent)
                                ) {
                                    Text(
                                        text = "Limpar",
                                        textAlign = TextAlign.Center,
                                        color = textGrey
                                    )
                                }
                            }
                            FlowRow(modifier = Modifier.fillMaxWidth()) {
                                filterList.forEach {
                                    CategoryFilter(filter = it)
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Button(onClick = {
                                    visible = false
                                    viewModel.searchStores(query, filterList)
                                }) {
                                    Text(text = "Aplicar filtros")
                                }
                            }

                        }
                    }
                }
            }
            Crossfade(targetState = searchState) { state ->
                when (state) {
                    is SearchStoresState.Loading -> {
                    }
                    is SearchStoresState.Success -> {
                        LazyColumn {
                            items(state.searchStoresList) { store ->
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
                    is SearchStoresState.Error -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Nenhuma loja encontrada",
                            )
                        }
                    }
                    is SearchStoresState.NoQuery -> {
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryFilter(filter: Filter) {
    val (selected, setSelected) = filter.enabled
    val backgroundColor by animateColorAsState(
        if (selected) backgroundBlue else Color.White
    )
    val textColor by animateColorAsState(
        if (selected) mainBlue else darkGrey
    )
    Box(modifier = Modifier
        .padding(5.dp)
        .shadow(1.dp, shape = RoundedCornerShape(20.dp))
        .background(backgroundColor)
        .clickable { setSelected(selected.not()) }
    ) {
        Text(
            text = filter.name,
            color = textColor,
            maxLines = 1,
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 6.dp
            )
        )
    }
}
