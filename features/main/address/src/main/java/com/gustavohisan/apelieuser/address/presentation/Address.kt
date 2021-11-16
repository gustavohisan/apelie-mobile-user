package com.gustavohisan.apelieuser.address.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gustavohisan.apelieuser.address.model.GetAddressFromCepState
import com.gustavohisan.apelieuser.address.model.InsertAddressErrorType
import com.gustavohisan.apelieuser.address.model.InsertAddressState
import com.gustavohisan.apelieuser.design.textGrey
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
internal fun Address(onBackClicked: () -> Unit, onDone: () -> Unit) {
    AddressLoader(onBackClicked = onBackClicked, onDone = onDone)
}

@Composable
private fun AddressLoader(
    onBackClicked: () -> Unit,
    onDone: () -> Unit,
    viewModel: AddressViewModel = getViewModel()
) {
    val insertAddressState: InsertAddressState by viewModel.insertAddressState.observeAsState(
        InsertAddressState.None
    )
    val getAddressByCepState: GetAddressFromCepState by viewModel.getAddressByCepState.observeAsState(
        GetAddressFromCepState.None
    )
    AddressScaffold(
        onBackClicked = onBackClicked,
        onDone = onDone,
        cepState = getAddressByCepState,
        insertState = insertAddressState,
        viewModel = viewModel
    )
}

@Composable
private fun AddressScaffold(
    onBackClicked: () -> Unit,
    onDone: () -> Unit,
    cepState: GetAddressFromCepState,
    insertState: InsertAddressState,
    viewModel: AddressViewModel
) {
    val (city, setCity) = rememberSaveable { mutableStateOf("") }
    val (complement, setComplement) = rememberSaveable { mutableStateOf("") }
    val (district, setDistrict) = rememberSaveable { mutableStateOf("") }
    val (number, setNumber) = rememberSaveable { mutableStateOf("") }
    val (state, setState) = rememberSaveable { mutableStateOf("") }
    val (street, setStreet) = rememberSaveable { mutableStateOf("") }
    val (zipCode, setZipCode) = rememberSaveable { mutableStateOf("") }
    var isRegistering by rememberSaveable { mutableStateOf(false) }
    var hasCityError by rememberSaveable { mutableStateOf(false) }
    var hasComplementError by rememberSaveable { mutableStateOf(false) }
    var hasDistrictError by rememberSaveable { mutableStateOf(false) }
    var hasNumberError by rememberSaveable { mutableStateOf(false) }
    var hasStateError by rememberSaveable { mutableStateOf(false) }
    var hasStreetError by rememberSaveable { mutableStateOf(false) }
    var hasZipCodeError by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    when (cepState) {
        is GetAddressFromCepState.Success -> {
            cepState.cepAddress?.let {
                setStreet(it.street)
                setState(it.state)
                setCity(it.city)
                setDistrict(it.district)
            }
        }
        GetAddressFromCepState.Empty -> {
            setStreet("")
            setState("")
            setCity("")
            setDistrict("")
        }
        GetAddressFromCepState.Error -> {
            setStreet("")
            setState("")
            setCity("")
            setDistrict("")
        }
        GetAddressFromCepState.None -> {
            setStreet("")
            setState("")
            setCity("")
            setDistrict("")
        }
    }

    when (insertState) {
        is InsertAddressState.Success -> onDone()
        is InsertAddressState.Error -> {
            insertState.errors.forEach { error ->
                when (error) {
                    InsertAddressErrorType.INVALID_ZIP_CODE -> {
                        hasZipCodeError = true
                    }
                    InsertAddressErrorType.INVALID_STREET -> {
                        hasStreetError = true
                    }
                    InsertAddressErrorType.INVALID_STATE -> {
                        hasStateError = true
                    }
                    InsertAddressErrorType.INVALID_NUMBER -> {
                        hasNumberError = true
                    }
                    InsertAddressErrorType.INVALID_DISTRICT -> {
                        hasDistrictError = true
                    }
                    InsertAddressErrorType.INVALID_CITY -> {
                        hasCityError = true
                    }
                    InsertAddressErrorType.SERVER_UNAVAILABLE -> {
                        Timber.e("Server unavailable")
                    }
                }
            }
            isRegistering = false
        }
        is InsertAddressState.None -> {
            hasCityError = false
            hasComplementError = false
            hasDistrictError = false
            hasNumberError = false
            hasStateError = false
            hasStreetError = false
            hasZipCodeError = false
            isRegistering = false
        }
        is InsertAddressState.Loading -> {
            hasCityError = false
            hasComplementError = false
            hasDistrictError = false
            hasNumberError = false
            hasStateError = false
            hasStreetError = false
            hasZipCodeError = false
            isRegistering = true
        }
    }
    Scaffold(topBar = { AddressTopBar(onBackPressed = onBackClicked) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                value = zipCode,
                onValueChange = { value ->
                    viewModel.getAddressByCep(value)
                    setZipCode(value)
                },
                singleLine = true,
                label = { Text("CEP") },
                isError = hasZipCodeError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasZipCodeError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "CEP inválido",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                value = street,
                onValueChange = { value -> setStreet(value) },
                singleLine = true,
                label = { Text("Logradouro") },
                isError = hasStreetError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasStreetError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Logradouro inválido",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                value = number,
                onValueChange = { value -> setNumber(value) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Número") },
                isError = hasNumberError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasNumberError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Número inválido",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                value = complement,
                onValueChange = { value -> setComplement(value) },
                singleLine = true,
                label = { Text("Complemento") },
                isError = hasComplementError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasComplementError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Complemento inválido",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                value = district,
                onValueChange = { value -> setDistrict(value) },
                singleLine = true,
                label = { Text("Bairro") },
                isError = hasDistrictError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasDistrictError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Bairro inválido",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                value = city,
                onValueChange = { value -> setCity(value) },
                singleLine = true,
                label = { Text("Cidade") },
                isError = hasCityError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasCityError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Cidade inválida",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                value = state,
                onValueChange = { value -> if (value.length <= 2) setState(value) },
                singleLine = true,
                label = { Text("Estado") },
                isError = hasZipCodeError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasStateError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Estado inválido",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            Button(
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewModel.insertAddress(
                        city,
                        complement,
                        district,
                        number,
                        state,
                        street,
                        zipCode
                    )
                },
                enabled = isRegistering.not()
            ) {
                Text(
                    text = "CADASTRAR ENDEREÇO",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun AddressTopBar(
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = "Cadastrar novo endereço",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = textGrey
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(Icons.Filled.ArrowBack, "Back")
            }
        },
    )
}
