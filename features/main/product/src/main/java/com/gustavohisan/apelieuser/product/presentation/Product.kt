package com.gustavohisan.apelieuser.product.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.gustavohisan.apelieuser.design.*
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
    onBackClicked: () -> Unit
) {
    ProductScaffold(onAddToCardSuccess = onAddToCardSuccess, onBackClicked = onBackClicked)
}

@ExperimentalPagerApi
@Composable
private fun ProductScaffold(
    onAddToCardSuccess: () -> Unit,
    onBackClicked: () -> Unit
) {
    val firstImage =
        rememberImagePainter(data = "https://tokstok.vtexassets.com/arquivos/ids/1777930-300-300/Cadeira-Amendoa-Brisa.jpg?v=637006142412630000")
    val secondImage =
        rememberImagePainter(data = "https://tokstok.vtexassets.com/arquivos/ids/1777930-300-300/Cadeira-Amendoa-Brisa.jpg?v=637006142412630000")
    val (description, setDescription) = rememberSaveable { mutableStateOf("") }
    val listImages = listOf(firstImage, secondImage)
    val pagerState = rememberPagerState(pageCount = 2)
    var productSelectedQuantity by remember { mutableStateOf(1) }
    var quantityDropdownExpanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState(0)
    Scaffold {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            HorizontalPager(modifier = Modifier.background(darkGrey), state = pagerState) { page ->
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    painter = listImages[page],
                    contentDescription = "aaa"
                )
            }
            HorizontalPagerIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                pagerState = pagerState
            )
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Cadeira de madeira",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Descrição do produto",
                    overflow = TextOverflow.Ellipsis,
                    color = textGrey,
                    fontSize = 12.sp,
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "R$10,00",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = moneyGreen,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Divider(modifier = Modifier.padding(vertical = 20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
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
                                },
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
                            DropdownMenuItem(onClick = {
                                productSelectedQuantity = 1
                                quantityDropdownExpanded = false
                            }) {
                                Text(text = "1")
                            }
                            DropdownMenuItem(onClick = {
                                productSelectedQuantity = 2
                                quantityDropdownExpanded = false
                            }) {
                                Text(text = "2")
                            }
                            DropdownMenuItem(onClick = {
                                productSelectedQuantity = 3
                                quantityDropdownExpanded = false
                            }) {
                                Text(text = "3")
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
                        .fillMaxWidth(),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "ADICIONAR AO CARRINHO", fontWeight = FontWeight.Bold)
                }
            }
        }
        BackButton(onBackClicked = onBackClicked)
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
