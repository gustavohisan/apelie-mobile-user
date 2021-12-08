package com.gustavohisan.apelieuser.checkout.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gustavohisan.apelieuser.checkout.model.Address
import com.gustavohisan.apelieuser.checkout.model.CheckoutItemsFromCartState
import com.gustavohisan.apelieuser.checkout.model.GetUserAddressesState
import com.gustavohisan.apelieuser.design.mainBlue
import com.gustavohisan.apelieuser.design.textGrey
import org.koin.androidx.compose.getViewModel

@Composable
fun Checkout(
    onBackClicked: () -> Unit,
    onConfirmed: () -> Unit,
    onRegisterAddressClicked: () -> Unit
) {
    CheckoutLoader(
        onBackClicked = onBackClicked,
        onConfirmed = onConfirmed,
        onRegisterAddressClicked = onRegisterAddressClicked
    )
}

@Composable
private fun CheckoutLoader(
    onBackClicked: () -> Unit,
    onConfirmed: () -> Unit,
    onRegisterAddressClicked: () -> Unit,
    viewModel: CheckoutViewModel = getViewModel()
) {
    val cartItemsState: GetUserAddressesState by viewModel.getUserAddressesState.observeAsState(
        GetUserAddressesState.Loading
    )
    val checkoutState: CheckoutItemsFromCartState by viewModel.checkoutCartState.observeAsState(
        CheckoutItemsFromCartState.None
    )
    viewModel.getUserAddressList()
    when (checkoutState) {
        is CheckoutItemsFromCartState.Loading -> {
        }
        is CheckoutItemsFromCartState.Error -> {
        }
        is CheckoutItemsFromCartState.None -> {
        }
        is CheckoutItemsFromCartState.Success -> {
            viewModel.resetState()
            onConfirmed()
        }
    }
    CheckoutScaffold(
        onBackClicked = onBackClicked,
        onRegisterAddressClicked = onRegisterAddressClicked,
        state = cartItemsState,
        viewModel = viewModel
    )
}

@Composable
private fun CheckoutScaffold(
    onBackClicked: () -> Unit,
    onRegisterAddressClicked: () -> Unit,
    state: GetUserAddressesState,
    viewModel: CheckoutViewModel
) {
    val scrollState = rememberScrollState()
    val paymentScrollState = rememberScrollState()
    val addressScrollState = rememberScrollState()
    var selectedPaymentMethod by rememberSaveable { mutableStateOf("") }
    val (selectedAddress, setSelectedAddress) = rememberSaveable { mutableStateOf(0) }
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClicked) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
            Text(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
                text = "Métodos de pagamento",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(paymentScrollState)
            ) {
                val pixBorderColor by animateColorAsState(
                    if (selectedPaymentMethod == "Pix") mainBlue else Color.White
                )
                Row(
                    Modifier
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                        .clickable(onClick = { selectedPaymentMethod = "Pix" })
                        .border(3.dp, pixBorderColor, shape = RoundedCornerShape(10.dp))
                        .shadow(2.dp, shape = RoundedCornerShape(10.dp))
                        .width(180.dp)
                        .height(120.dp)
                        .padding(horizontal = 15.dp, vertical = 15.dp)
                ) {
                    Text(
                        text = "Pix",
                        color = textGrey,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp
                    )
                }
                val boletoBorderColor by animateColorAsState(
                    if (selectedPaymentMethod == "Boleto") mainBlue else Color.White
                )
                Row(
                    Modifier
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                        .clickable(onClick = { selectedPaymentMethod = "Boleto" })
                        .border(3.dp, boletoBorderColor, shape = RoundedCornerShape(10.dp))
                        .shadow(2.dp, shape = RoundedCornerShape(10.dp))
                        .width(180.dp)
                        .height(120.dp)
                        .padding(horizontal = 15.dp, vertical = 15.dp)
                ) {
                    Text(
                        text = "Boleto",
                        color = textGrey,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp
                    )
                }
                val creditCardBorderColor by animateColorAsState(
                    if (selectedPaymentMethod == "Credit_card") mainBlue else Color.White
                )
                Row(
                    Modifier
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                        .clickable(onClick = { selectedPaymentMethod = "Credit_card" })
                        .border(3.dp, creditCardBorderColor, shape = RoundedCornerShape(10.dp))
                        .shadow(2.dp, shape = RoundedCornerShape(10.dp))
                        .width(180.dp)
                        .height(120.dp)
                        .padding(horizontal = 15.dp, vertical = 15.dp)
                ) {
                    Text(
                        text = "Cartão de crédito",
                        color = textGrey,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp
                    )
                }
            }
            Divider(modifier = Modifier.padding(vertical = 20.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
                text = "Endereço de entrega",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Crossfade(targetState = state) { state ->
                when (state) {
                    is GetUserAddressesState.Empty -> {
                        NewAddress(onRegisterAddressClicked = onRegisterAddressClicked)
                    }
                    GetUserAddressesState.Error -> {
                    }
                    GetUserAddressesState.Loading -> {
                    }
                    is GetUserAddressesState.Success -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(addressScrollState)
                        ) {
                            state.addresses.forEach { address ->
                                Address(
                                    address,
                                    setSelectedAddress,
                                    selectedAddress
                                )
                            }
                            NewAddress(onRegisterAddressClicked = onRegisterAddressClicked)
                        }
                    }
                }
            }
            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                onClick = { viewModel.finishCheckout(selectedAddress, selectedPaymentMethod) },
                enabled = selectedAddress != 0 && selectedPaymentMethod != ""
            ) {
                Text(text = "FINALIZAR COMPRA", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun Address(address: Address, setSelectedAddress: (Int) -> Unit, selectedAddress: Int) {
    val borderColor by animateColorAsState(
        if (selectedAddress == address.addressId) mainBlue else Color.White
    )
    Column(
        Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .clickable(onClick = { setSelectedAddress(address.addressId) })
            .border(3.dp, borderColor, shape = RoundedCornerShape(10.dp))
            .shadow(2.dp, shape = RoundedCornerShape(10.dp))
            .width(180.dp)
            .height(120.dp)
            .padding(horizontal = 15.dp, vertical = 15.dp)
    ) {
        Text(
            text = "${address.street}, ${address.number}",
            maxLines = 2,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp
        )
        Text(
            text = address.district,
            maxLines = 1,
            color = textGrey,
            overflow = TextOverflow.Ellipsis,
            fontSize = 11.sp
        )
        Text(
            text = "${address.city}, ${address.state}",
            maxLines = 2,
            color = textGrey,
            overflow = TextOverflow.Ellipsis,
            fontSize = 11.sp
        )
    }
}

@Composable
private fun NewAddress(onRegisterAddressClicked: () -> Unit) {
    Column(
        Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .clickable(onClick = { onRegisterAddressClicked() })
            .border(3.dp, Color.White, shape = RoundedCornerShape(10.dp))
            .shadow(2.dp, shape = RoundedCornerShape(10.dp))
            .width(180.dp)
            .height(120.dp)
            .padding(horizontal = 15.dp, vertical = 15.dp)
    ) {
        Text(
            text = "Cadastrar um novo endereço",
            color = textGrey,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp
        )
    }
}
