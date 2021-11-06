package com.gustavohisan.apelieuser.product.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.gustavohisan.apelieuser.design.*
import com.gustavohisan.apelieuser.product.model.InsertProductInCartState
import com.gustavohisan.apelieuser.product.model.Product
import com.gustavohisan.apelieuser.product.model.ProductState
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@ExperimentalPagerApi
@Composable
internal fun Product(
    productId: Int,
    onAddToCardSuccess: () -> Unit,
    onBackClicked: () -> Unit
) {
    ProductLoader(
        productId = productId,
        onAddToCardSuccess = onAddToCardSuccess,
        onBackClicked = onBackClicked
    )
}

@ExperimentalPagerApi
@Composable
private fun ProductLoader(
    productId: Int,
    onAddToCardSuccess: () -> Unit,
    onBackClicked: () -> Unit,
    viewModel: ProductViewModel = getViewModel()
) {
    viewModel.getProductData(productId)
    val productState: ProductState by viewModel.productState.observeAsState(ProductState.Loading)
    val insertProductInCartScrollState: InsertProductInCartState by viewModel.insertProductInCartState.observeAsState(
        InsertProductInCartState.NotInserting
    )
    ProductScaffold(
        productState = productState,
        onAddToCardSuccess = onAddToCardSuccess,
        onBackClicked = onBackClicked,
        insertProductInCartState = insertProductInCartScrollState,
        viewModel = viewModel
    )
}

@ExperimentalPagerApi
@Composable
private fun ProductScaffold(
    productState: ProductState,
    onAddToCardSuccess: () -> Unit,
    onBackClicked: () -> Unit,
    insertProductInCartState: InsertProductInCartState,
    viewModel: ProductViewModel
) {
    ProvideWindowInsets {
        Scaffold {
            Crossfade(targetState = productState) {
                when (it) {
                    is ProductState.Loading -> {
                    }
                    is ProductState.Error -> {
                    }
                    is ProductState.Success -> {
                        ProductInfo(product = it.product, viewModel = viewModel)
                    }
                }
            }
            Crossfade(targetState = insertProductInCartState) {
                when (it) {
                    is InsertProductInCartState.Loading -> {
                    }
                    is InsertProductInCartState.Error -> {
                    }
                    is InsertProductInCartState.Success -> {
                        onAddToCardSuccess()
                    }
                    is InsertProductInCartState.NotInserting -> {
                    }
                }
            }
            BackButton(onBackClicked = onBackClicked)
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun ProductInfo(product: Product, viewModel: ProductViewModel) {
    val (description, setDescription) = rememberSaveable { mutableStateOf("") }
    val listImages = product.images.map { rememberImagePainter(data = it) }
    var productSelectedQuantity by remember { mutableStateOf(1) }
    var quantityDropdownExpanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState(0)
    val pagerState = rememberPagerState(0)
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        HorizontalPager(
            modifier = Modifier.background(darkGrey),
            state = pagerState,
            count = listImages.size
        ) { page ->
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                painter = listImages[page],
                contentDescription = null
            )
        }
        if (listImages.size > 1) {
            HorizontalPagerIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp),
                pagerState = pagerState
            )
        }
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = product.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = product.description,
                overflow = TextOverflow.Ellipsis,
                color = textGrey,
                fontSize = 12.sp,
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "R$ ${"%.2f".format(product.price)}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = moneyGreen,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Divider(modifier = Modifier.padding(vertical = 20.dp))
            if (product.quantity > 1) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 5.dp),
                        text = "Quantidade",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = textGrey,
                        fontSize = 16.sp,
                    )
                    Column {
                        Row(
                            modifier = Modifier
                                .width(60.dp)
                                .clickable {
                                    quantityDropdownExpanded = true
                                }
                                .padding(vertical = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "$productSelectedQuantity",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = textGrey,
                                fontSize = 16.sp
                            )
                            Icon(
                                imageVector = if (quantityDropdownExpanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                                contentDescription = ""
                            )
                        }
                        DropdownMenu(
                            modifier = Modifier.width(60.dp),
                            expanded = quantityDropdownExpanded,
                            onDismissRequest = { quantityDropdownExpanded = false })
                        {
                            for (i in 1..product.quantity) {
                                DropdownMenuItem(onClick = {
                                    productSelectedQuantity = i
                                    quantityDropdownExpanded = false
                                }) {
                                    Text(text = "$i")
                                }
                            }
                        }
                    }
                }
            }
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Comentários adicionais",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = textGrey,
                fontSize = 16.sp
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(140.dp)
                    .fillMaxWidth(),
                value = description,
                onValueChange = { value -> setDescription(value) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = textGrey,
                    cursorColor = mainGrey,
                    focusedBorderColor = mainGrey,
                    unfocusedBorderColor = backgroundGrey
                )
            )
            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .navigationBarsWithImePadding(),
                onClick = {
                    viewModel.addProductToCart(
                        product.id,
                        description,
                        productSelectedQuantity
                    )
                }
            ) {
                Text(text = "ADICIONAR AO CARRINHO", fontWeight = FontWeight.Bold)
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
