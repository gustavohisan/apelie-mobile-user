package com.gustavohisan.apelieuser.orders.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
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
import com.gustavohisan.apelieuser.design.*
import com.gustavohisan.apelieuser.orders.model.GetOrderByIdState
import com.gustavohisan.apelieuser.orders.model.ItemList
import com.gustavohisan.apelieuser.orders.model.Order
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
internal fun Order(orderId: Int, onBackClicked: () -> Unit) {
    OrderLoader(orderId = orderId, onBackClicked = onBackClicked)
}

@Composable
private fun OrderLoader(
    orderId: Int,
    onBackClicked: () -> Unit,
    viewModel: OrderViewModel = getViewModel()
) {
    val state by viewModel.getOrderState.observeAsState(GetOrderByIdState.Loading)
    if (state is GetOrderByIdState.Loading) {
        viewModel.loadOrderById(orderId)
    }
    val rateResult by viewModel.rateOrderResult.observeAsState(false)
    OrderScaffold(
        onBackClicked = onBackClicked,
        orderId = orderId,
        state = state,
        rateResult = rateResult,
        viewModel = viewModel
    )
}

@Composable
private fun OrderScaffold(
    onBackClicked: () -> Unit,
    orderId: Int,
    state: GetOrderByIdState,
    rateResult: Boolean,
    viewModel: OrderViewModel
) {
    Scaffold(topBar = { OrderTopBar(orderId = orderId, onBackClicked = onBackClicked) }) {
        Crossfade(targetState = state) { state ->
            when (state) {
                is GetOrderByIdState.Loading -> {
                }
                is GetOrderByIdState.Success -> {
                    OrderData(order = state.order, viewModel = viewModel, rateResult = rateResult)
                }
                is GetOrderByIdState.Error -> {
                }
            }
        }
    }
}

@Composable
private fun OrderData(order: Order, viewModel: OrderViewModel, rateResult: Boolean) {
    val (copyCode, setCopyCode) = remember { mutableStateOf(false) }
    if (copyCode) {
        CopyToClipboard(text = order.trackingCode)
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Status da entrega",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(10.dp))
        when (order.status) {
            "Canceled" -> CancelledStatus()
            "Awaiting_payment" -> AwaitingPaymentStatus()
            "Awaiting_shipping" -> ApprovedStatus()
            "Shipping" -> SentStatus(trackingCode = order.trackingCode, setCopyCode = setCopyCode)
            "Finished" -> {
                ReceivedStatus(
                    trackingCode = order.trackingCode,
                    setCopyCode = setCopyCode
                )
                Review(
                    orderId = order.orderId,
                    hasReview = order.hasReview,
                    viewModel = viewModel,
                    rateState = rateResult
                )
            }
        }
        Divider(modifier = Modifier.padding(vertical = 20.dp))
        Text(
            text = "Informações do pedido",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(20.dp))
        order.itemList.forEach { itemList -> OrderProduct(itemList) }
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(top = 20.dp, bottom = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total: ",
                    color = textGrey,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "R$${
                        "%.2f".format(order.itemList.map { it.quantity * it.product.price }
                            .sum())
                    }",
                    color = textGrey,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
        }
        Divider(modifier = Modifier.padding(vertical = 20.dp))
        Text(
            text = "Vendido por",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(20.dp))
        OrderStore(
            state = order.store.state,
            city = order.store.city,
            rating = order.store.rating,
            storeUrl = order.store.logoUrl,
            bannerUrl = order.store.bannerUrl,
            categories = order.store.category,
            name = order.store.name
        )
        Divider(modifier = Modifier.padding(vertical = 20.dp))
        Text(
            text = "Endereço de entrega",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = order.address.street + ", " + order.address.number,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = order.address.district + ", " + order.address.zipCode.substring(
                0,
                5
            ) + "-" + order.address.zipCode.substring(5, order.address.zipCode.length),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = darkGrey,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = order.address.city + " - " + order.address.state,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = darkGrey,
            fontSize = 14.sp
        )
        Divider(modifier = Modifier.padding(vertical = 20.dp))
        Text(
            text = "Informações de pagamento",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = when (order.paymentMethod) {
                "Pix" -> "Pix"
                "Credit_card" -> "Cartão de crédito"
                "Boleto" -> "Boleto"
                else -> ""
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun CopyToClipboard(text: String) {
    LocalClipboardManager.current.setText(AnnotatedString(text))
}

@Composable
private fun AwaitingPaymentStatus() {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            modifier = Modifier.weight(1F),
            text = "Aguardando pagamento",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = mainBlue,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Aprovado",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Enviado",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Entregue",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        progress = 0.125F,
        color = mainBlue,
        backgroundColor = mainBlue.copy(alpha = 0.25f)
    )
}

@Composable
private fun ApprovedStatus() {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            modifier = Modifier.weight(1F),
            text = "Aguardando pagamento",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = mainBlue,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Aprovado",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = mainBlue,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Enviado",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Entregue",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        progress = 0.375F,
        color = mainBlue,
        backgroundColor = mainBlue.copy(alpha = 0.25f)
    )
}

@Composable
private fun CancelledStatus() {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            modifier = Modifier.weight(1F),
            text = "Aguardando pagamento",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.Red,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Cancelada",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Red,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Enviado",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Entregue",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        progress = 0.375F,
        color = Color.Red,
        backgroundColor = Color.Red.copy(alpha = 0.25f)
    )
}

@Composable
private fun SentStatus(trackingCode: String, setCopyCode: (Boolean) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1F),
            text = "Código de rastreio",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 14.sp,
        )
        Row(
            modifier = Modifier.weight(1F),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = trackingCode,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
                fontSize = 12.sp,
            )
            IconButton(onClick = { setCopyCode(true) }) {
                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null)
            }
        }
    }
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            modifier = Modifier.weight(1F),
            text = "Aguardando pagamento",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = mainBlue,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Aprovado",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = mainBlue,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Enviado",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = mainBlue,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Entregue",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        progress = 0.625F,
        color = mainBlue,
        backgroundColor = mainBlue.copy(alpha = 0.25f)
    )
}

@Composable
private fun ReceivedStatus(trackingCode: String, setCopyCode: (Boolean) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1F),
            text = "Código de rastreio",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textGrey,
            fontSize = 14.sp,
        )
        Row(
            modifier = Modifier.weight(1F),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = trackingCode,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
                fontSize = 12.sp,
            )
            IconButton(
                onClick = { setCopyCode(true) }) {
                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null)
            }
        }
    }
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            modifier = Modifier.weight(1F),
            text = "Aguardando pagamento",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = mainBlue,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Aprovado",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = mainBlue,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Enviado",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = mainBlue,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Entregue",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = mainBlue,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        progress = 1F,
        color = mainBlue,
        backgroundColor = mainBlue.copy(alpha = 0.25f)
    )
}

@Composable
private fun OrderTopBar(orderId: Int, onBackClicked: () -> Unit) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = "Compra #$orderId",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = textGrey
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(Icons.Filled.Close, "Close")
            }
        },
    )
}


@Composable
private fun OrderProduct(orderItem: ItemList) {
    val productImage = rememberImagePainter(data = orderItem.product.images.firstOrNull() ?: "")
    var isExpanded by remember { mutableStateOf(false) }
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = isExpanded.not() }
            .padding(vertical = 5.dp)
            .animateContentSize()) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
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
                                .width(60.dp)
                                .height(60.dp)
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
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(bottom = 15.dp),
                            text = orderItem.product.name,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Quantidade: ${orderItem.quantity}",
                                maxLines = 1,
                                overflow = TextOverflow.Visible,
                                color = darkGrey,
                                fontSize = 14.sp,
                            )
                            Text(
                                text = "R$ ${"%.2f".format(orderItem.product.price * orderItem.quantity)}",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = textGrey,
                                fontSize = 18.sp
                            )
                        }
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
                        Text(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            text = if (orderItem.description.isEmpty()) "Nenhum comentário" else orderItem.description,
                            overflow = TextOverflow.Visible,
                            color = textGrey,
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OrderStore(
    bannerUrl: String,
    storeUrl: String,
    name: String,
    categories: List<String>,
    city: String,
    state: String,
    rating: Float
) {
    val bannerImage = rememberImagePainter(data = bannerUrl)
    val storeImage = rememberImagePainter(data = storeUrl)
    Box(
        Modifier
            .fillMaxWidth()
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
        Column {
            Row(modifier = Modifier.padding(top = 50.dp, start = 10.dp, bottom = 10.dp)) {
                Image(
                    painter = storeImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .border(
                            width = 4.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
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
                            text = name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = categories.joinToString(separator = ", ", limit = 3),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = darkGrey,
                            fontSize = 12.sp,
                        )
                        Text(
                            text = "${city}, $state",
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
                            text = "%.1f".format(rating),
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

@Composable
private fun Review(
    orderId: Int,
    hasReview: Boolean,
    viewModel: OrderViewModel,
    rateState: Boolean
) {
    if (hasReview.not()) {
        var rating by rememberSaveable { mutableStateOf(1) }
        val (description, setDescription) = rememberSaveable { mutableStateOf("") }
        var enabled by rememberSaveable { mutableStateOf(true) }
        Divider(modifier = Modifier.padding(vertical = 20.dp))
        Text(
            text = "Avalie sua compra",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (rateState) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Obrigado por avaliar!",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = textGrey,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        } else {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Icon(
                    imageVector = Icons.Default.Star,
                    modifier = Modifier
                        .noRippleClickable { rating = 1 }
                        .size(50.dp),
                    contentDescription = "",
                    tint = ratingYellow
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    modifier = Modifier
                        .noRippleClickable { rating = 2 }
                        .size(50.dp),
                    contentDescription = "",
                    tint = if (rating > 1) ratingYellow else darkGrey
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    modifier = Modifier
                        .noRippleClickable { rating = 3 }
                        .size(50.dp),
                    contentDescription = "",
                    tint = if (rating > 2) ratingYellow else darkGrey
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    modifier = Modifier
                        .noRippleClickable { rating = 4 }
                        .size(50.dp),
                    contentDescription = "",
                    tint = if (rating > 3) ratingYellow else darkGrey
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    modifier = Modifier
                        .noRippleClickable { rating = 5 }
                        .size(50.dp),
                    contentDescription = "",
                    tint = if (rating > 4) ratingYellow else darkGrey
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 15.dp)
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
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
                onClick = {
                    enabled = false
                    viewModel.rateFinishedOrder(orderId, rating, description)
                },
                enabled = enabled
            ) {
                Text(text = "ENVIAR AVALIAÇÃO", fontWeight = FontWeight.Bold)
            }
        }
    }
}

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}
