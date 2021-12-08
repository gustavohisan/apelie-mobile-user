package com.gustavohisan.apelieuser.orders.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.gustavohisan.apelieuser.design.*
import com.gustavohisan.apelieuser.design.mainGrey
import com.gustavohisan.apelieuser.orders.model.GetUserOrdersState
import com.gustavohisan.apelieuser.orders.model.Order
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@ExperimentalFoundationApi
@Composable
fun Orders(onOrderClicked: (Int) -> Unit) {
    OrdersLoader(onOrderClicked = onOrderClicked)
}

@ExperimentalFoundationApi
@Composable
private fun OrdersLoader(
    onOrderClicked: (Int) -> Unit,
    viewModel: OrdersViewModel = getViewModel()
) {
    val state by viewModel.getUserOrdersState.observeAsState(GetUserOrdersState.Loading)
    if (state is GetUserOrdersState.Loading) {
        viewModel.loadOrders()
    }
    OrdersScaffold(onOrderClicked = onOrderClicked, state = state, viewModel = viewModel)
}

@ExperimentalFoundationApi
@Composable
private fun OrdersScaffold(
    onOrderClicked: (Int) -> Unit,
    state: GetUserOrdersState,
    viewModel: OrdersViewModel
) {
    Scaffold(topBar = { OrdersTopBar() }) {
        Crossfade(targetState = state) {
            when (it) {
                is GetUserOrdersState.Loading -> {
                }
                is GetUserOrdersState.Error -> {
                }
                is GetUserOrdersState.Success -> {
                    val groupedOrders = viewModel.getGroupedOrders(it.orders)
                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn {
                            groupedOrders.forEach { (header, orders) ->
                                stickyHeader {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(40.dp)
                                            .background(backgroundGrey),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(start = 20.dp),
                                            text = header,
                                            color = textGrey
                                        )
                                    }
                                }
                                itemsIndexed(orders) { index, order ->
                                    OrderItem(order = order, onOrderClicked = onOrderClicked)
                                    if ((index < it.orders.lastIndex)) {
                                        Divider()
                                    }
                                }
                            }
                        }
                    }
                }
                is GetUserOrdersState.Empty -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.ShoppingBag,
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.padding(5.dp),
                                text = "Nenhuma compra realizada"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderItem(order: Order, onOrderClicked: (Int) -> Unit) {
    val productImage = rememberImagePainter(
        data = order.itemList.firstOrNull()?.product?.images?.firstOrNull() ?: ""
    )
    val statusText = when (order.status.toUpperCase(Locale.current)) {
        "CANCELED" -> "Cancelado"
        "AWAITING_PAYMENT" -> "Aguardando pagamento"
        "AWAITING_SHIPPING" -> "Em despache"
        "SHIPPING" -> "Enviado"
        "FINISHED" -> "Entregue"
        else -> ""
    }
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { onOrderClicked(order.orderId) }
            .padding(horizontal = 10.dp, vertical = 5.dp)) {
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
                                .width(80.dp)
                                .height(80.dp)
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
                            .fillMaxHeight()
                            .weight(1F)
                            .padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Compra #${order.orderId}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = when (order.paymentMethod) {
                                "Pix" -> "Pix"
                                "Credit_card" -> "Cartão de crédito"
                                "Boleto" -> "Boleto"
                                else -> "Pagamento"
                            },
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = darkGrey,
                            fontSize = 12.sp,
                        )
                        Text(
                            text = order.address.street + ", " + order.address.number,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = darkGrey,
                            fontSize = 12.sp,
                        )
                        Spacer(modifier = Modifier.padding(vertical = 15.dp))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.65F)
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = statusText,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = darkGrey,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.padding(vertical = 10.dp))
                        Text(
                            text = "R$ ${"%.2f".format(order.totalValue)}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = textGrey,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun OrdersTopBar() {
    TopAppBar(
        backgroundColor = mainBlue,
        title = {
            Text(
                text = "Compras realizadas",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    )
}
