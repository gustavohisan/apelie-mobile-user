package com.gustavohisan.apelieuser.cart.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.gustavohisan.apelieuser.cart.model.CartItem
import com.gustavohisan.apelieuser.cart.model.EditProductInCartState
import com.gustavohisan.apelieuser.cart.model.GetItemsFromCartState
import com.gustavohisan.apelieuser.design.darkGrey
import com.gustavohisan.apelieuser.design.mainBlue
import com.gustavohisan.apelieuser.design.textGrey
import org.koin.androidx.compose.getViewModel

@Composable
internal fun Cart(onCheckoutClicked: () -> Unit) {
    CartLoader(onCheckoutClicked = onCheckoutClicked)
}

@Composable
private fun CartLoader(
    onCheckoutClicked: () -> Unit,
    viewModel: CartViewModel = getViewModel()
) {
    val cartItemsState: GetItemsFromCartState by viewModel.getItemsFromCartState.observeAsState(
        GetItemsFromCartState.Loading
    )
    val editProductInCartState: EditProductInCartState by viewModel.editCartItem.observeAsState(
        EditProductInCartState.None
    )
    val clearCartResult: Boolean by viewModel.clearCartItemsResult.observeAsState(false)
    if (cartItemsState is GetItemsFromCartState.Loading) {
        viewModel.getCartItems()
    }
    if (clearCartResult) {
        viewModel.resetClearCartState()
        viewModel.getCartItems()
    }
    CartScaffold(
        cartItemsState = cartItemsState,
        viewModel = viewModel,
        onCheckoutClicked = onCheckoutClicked,
        editProductState = editProductInCartState
    )
}

@Composable
private fun CartScaffold(
    cartItemsState: GetItemsFromCartState,
    viewModel: CartViewModel,
    onCheckoutClicked: () -> Unit,
    editProductState: EditProductInCartState
) {
    when (editProductState) {
        is EditProductInCartState.Success -> {
            viewModel.resetEditCartState()
            viewModel.getCartItems()
        }
        EditProductInCartState.Error -> {}
        EditProductInCartState.Loading -> {}
        EditProductInCartState.None -> {}
    }
    Scaffold {
        Crossfade(targetState = cartItemsState) {
            when (it) {
                is GetItemsFromCartState.Loading -> {
                }
                is GetItemsFromCartState.Error -> {
                }
                is GetItemsFromCartState.Success -> {
                    if (it.itemList.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = null
                                )
                                Text(
                                    modifier = Modifier.padding(5.dp),
                                    text = "Nenhum item no carrinho"
                                )
                            }
                        }
                    } else {
                        Box(modifier = Modifier.fillMaxSize()) {
                            LazyColumn {
                                item {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 20.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Produtos",
                                            color = Color.Black,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                        )
                                        TextButton(
                                            onClick = { viewModel.clearCartItems() },
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
                                }
                                items(it.itemList) { cartItem ->
                                    CartProduct(cartItem = cartItem, viewModel = viewModel)
                                }
                                item {
                                    Box(modifier = Modifier.fillMaxWidth()) {
                                        Row(
                                            modifier = Modifier
                                                .align(Alignment.CenterEnd)
                                                .padding(end = 20.dp, top = 20.dp, bottom = 20.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "Total: ",
                                                color = textGrey,
                                                fontSize = 18.sp
                                            )
                                            Text(
                                                text = "R$${
                                                    "%.2f".format(it.itemList.map { it.quantity * it.product.price }
                                                        .sum())
                                                }",
                                                color = mainBlue,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 26.sp
                                            )
                                        }
                                    }
                                }
                                item {
                                    Spacer(modifier = Modifier.height(60.dp))
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .height(60.dp)
                                    .fillMaxWidth()
                                    .shadow(5.dp)
                                    .align(Alignment.BottomCenter)
                                    .background(Color.White)
                            ) {
                                TextButton(
                                    onClick = { onCheckoutClicked() },
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .padding(horizontal = 25.dp)
                                        .height(40.dp)
                                        .width(100.dp),
                                    colors = ButtonDefaults.textButtonColors(
                                        contentColor = mainBlue,
                                        backgroundColor = mainBlue
                                    )
                                ) {
                                    Text(
                                        text = "Comprar",
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CartProduct(cartItem: CartItem, viewModel: CartViewModel) {
    val productImage = rememberImagePainter(data = cartItem.product.images.first())
    var isExpanded by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var quantity by remember { mutableStateOf(cartItem.quantity) }
    var description by remember { mutableStateOf(cartItem.description) }
    var quantityDropdownExpanded by remember { mutableStateOf(false) }
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = isExpanded.not() }
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .animateContentSize()) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Image(
                            painter = productImage,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .placeholder(
                                    visible = (productImage.state is ImagePainter.State.Success).not(),
                                    color = Color.LightGray,
                                    highlight = PlaceholderHighlight.fade(Color.White)
                                )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxHeight()
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(bottom = 15.dp),
                            text = cartItem.product.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        if (isEditing) {
                            Row(
                                modifier = Modifier
                                    .clickable {
                                        quantityDropdownExpanded = true
                                    }
                                    .padding(vertical = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "$quantity",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = textGrey,
                                    fontSize = 16.sp
                                )
                                Icon(
                                    imageVector = if (quantityDropdownExpanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                                    contentDescription = ""
                                )
                                DropdownMenu(
                                    modifier = Modifier.width(60.dp),
                                    expanded = quantityDropdownExpanded,
                                    onDismissRequest = { quantityDropdownExpanded = false })
                                {
                                    for (i in 1..cartItem.product.quantity) {
                                        DropdownMenuItem(onClick = {
                                            quantity = i
                                            quantityDropdownExpanded = false
                                        }) {
                                            Text(text = "$i")
                                        }
                                    }
                                }
                            }
                        } else {
                            Text(
                                text = "Quantidade: $quantity",
                                maxLines = 1,
                                overflow = TextOverflow.Visible,
                                color = darkGrey,
                                fontSize = 14.sp,
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(bottom = 10.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            if (isEditing) {
                                IconButton(
                                    onClick = {
                                        if (quantity != cartItem.quantity || description != cartItem.description) {
                                            viewModel.editCartItem(
                                                cartItem.id,
                                                quantity,
                                                description
                                            )
                                        }
                                        isEditing = false
                                        isExpanded = false
                                    },
                                ) {
                                    Icon(imageVector = Icons.Filled.Done, contentDescription = null)
                                }
                            } else {
                                IconButton(
                                    onClick = {
                                        isEditing = true
                                        isExpanded = true
                                    },
                                ) {
                                    Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
                                }
                                IconButton(
                                    onClick = {
                                        viewModel.editCartItem(
                                            cartItem.id,
                                            0,
                                            description
                                        )
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                        Text(
                            text = "R$ ${"%.2f".format(cartItem.product.price * cartItem.quantity)}",
                            maxLines = 1,
                            overflow = TextOverflow.Visible,
                            color = textGrey,
                            fontSize = 18.sp
                        )
                    }
                }
                if (isExpanded) {
                    Column(modifier = Modifier.padding(bottom = 15.dp)) {
                        Text(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            text = "Comentários adicionais",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        if (isEditing) {
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 5.dp),
                                value = description,
                                onValueChange = { value -> description = value },
                                singleLine = true,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = Color.Black
                                )
                            )
                        } else {
                            Text(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                text = if (description.isEmpty()) "Nenhum comentário" else description,
                                overflow = TextOverflow.Visible,
                                color = textGrey,
                                fontSize = 12.sp,
                            )
                        }
                    }
                }
            }
        }
        Divider(modifier = Modifier.fillMaxWidth())
    }
}
