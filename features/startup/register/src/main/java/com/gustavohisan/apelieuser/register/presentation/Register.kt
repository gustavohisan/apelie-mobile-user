package com.gustavohisan.apelieuser.register.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gustavohisan.apelieuser.design.ApelieTheme
import com.gustavohisan.apelieuser.design.mainBlue
import com.gustavohisan.apelieuser.register.R
import com.gustavohisan.apelieuser.register.model.RegisterErrorType
import com.gustavohisan.apelieuser.register.model.RegisterState
import org.koin.androidx.compose.getViewModel

@Composable
fun Register(
    onBackPressed: () -> Unit,
    onRegisterNewUser: () -> Unit
) {
    RegisterLoader(onBackPressed, onRegisterNewUser)
}

@Composable
private fun RegisterLoader(
    onBackPressed: () -> Unit,
    onRegisterNewUser: () -> Unit,
    viewModel: RegisterViewModel = getViewModel()
) {
    val state: RegisterState by viewModel.registerState.observeAsState(RegisterState.Default)
    RegisterScaffold(
        onBackPressed = onBackPressed,
        onRegisterNewUser = onRegisterNewUser,
        state = state,
        viewModel = viewModel,
    )
}

@Composable
private fun RegisterScaffold(
    onBackPressed: () -> Unit,
    onRegisterNewUser: () -> Unit,
    state: RegisterState,
    viewModel: RegisterViewModel,
) {
    val (email, setEmail) = rememberSaveable { mutableStateOf("") }
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }
    val (name, setName) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, setConfirmPassword) = rememberSaveable { mutableStateOf("") }
    var isRegistering by rememberSaveable { mutableStateOf(false) }
    var hasNameError by rememberSaveable { mutableStateOf(false) }
    var hasEmailError by rememberSaveable { mutableStateOf(false) }
    var hasPasswordError by rememberSaveable { mutableStateOf(false) }
    var hasConfirmPasswordError by rememberSaveable { mutableStateOf(false) }
    var nameErrorMessage by rememberSaveable { mutableStateOf("Erro") }
    var emailErrorMessage by rememberSaveable { mutableStateOf("Erro") }
    var passwordErrorMessage by rememberSaveable { mutableStateOf("Erro") }
    var confirmPasswordErrorMessage by rememberSaveable { mutableStateOf("Erro") }

    when (state) {
        is RegisterState.Success -> onRegisterNewUser()
        is RegisterState.Error -> {
            state.errorTypeList.forEach { error ->
                when (error) {
                    RegisterErrorType.PASSWORDS_DONT_MATCH -> {
                        confirmPasswordErrorMessage =
                            stringResource(id = R.string.register_error_passwords_dont_match)
                        hasConfirmPasswordError = true
                    }
                    RegisterErrorType.ALREADY_SUBSCRIBED -> {
                        emailErrorMessage =
                            stringResource(id = R.string.register_error_email_already_subscribed)
                        hasEmailError = true
                    }
                    RegisterErrorType.SERVER_UNAVAILABLE -> showToastError(LocalContext.current)
                    RegisterErrorType.INVALID_EMAIL -> {
                        emailErrorMessage =
                            stringResource(id = R.string.register_error_invalid_email)
                        hasEmailError = true
                    }
                    RegisterErrorType.INVALID_PASSWORD -> {
                        passwordErrorMessage =
                            stringResource(id = R.string.register_error_invalid_password)
                        hasPasswordError = true
                    }
                    RegisterErrorType.INVALID_NAME -> {
                        nameErrorMessage = stringResource(id = R.string.register_error_invalid_name)
                        hasNameError = true
                    }
                }
            }
            isRegistering = false
        }
        is RegisterState.Default -> {
            hasEmailError = false
            hasPasswordError = false
            hasNameError = false
            hasConfirmPasswordError = false
            isRegistering = false
        }
        is RegisterState.Checking -> {
            hasEmailError = false
            hasPasswordError = false
            hasNameError = false
            hasConfirmPasswordError = false
            isRegistering = true
        }
    }
    Scaffold(topBar = { RegisterTopBar(onBackPressed) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { value -> setName(value) },
                singleLine = true,
                label = { Text(stringResource(R.string.register_textinput_hint_name)) },
                isError = hasNameError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasNameError) {
                Text(
                    text = nameErrorMessage,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            OutlinedTextField(
                modifier = Modifier.padding(top = 20.dp),
                value = email,
                onValueChange = { value -> setEmail(value) },
                singleLine = true,
                label = { Text(stringResource(R.string.register_textinput_hint_email)) },
                isError = hasEmailError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasEmailError) {
                Text(
                    text = emailErrorMessage,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            OutlinedTextField(
                modifier = Modifier.padding(top = 20.dp),
                value = password,
                onValueChange = { value -> setPassword(value) },
                singleLine = true,
                label = { Text(stringResource(R.string.register_textinput_hint_password)) },
                isError = hasPasswordError,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasPasswordError) {
                Text(
                    text = passwordErrorMessage,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            OutlinedTextField(
                modifier = Modifier.padding(top = 20.dp),
                value = confirmPassword,
                onValueChange = { value -> setConfirmPassword(value) },
                singleLine = true,
                label = { Text(stringResource(R.string.register_textinput_hint_confirm_password)) },
                isError = hasConfirmPasswordError,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasConfirmPasswordError) {
                Text(
                    text = confirmPasswordErrorMessage,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            Button(
                modifier = Modifier
                    .padding(bottom = 20.dp, top = 20.dp)
                    .fillMaxWidth(),
                onClick = { viewModel.registerNewUser(email, password, name, confirmPassword) },
                enabled = isRegistering.not()
            ) {
                Text(
                    text = stringResource(R.string.register_button_sumit_text),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun RegisterTopBar(
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = stringResource(R.string.register_toolbar_title),
                color = mainBlue
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(Icons.Filled.ArrowBack, "Back")
            }
        },
    )
}

private fun showToastError(context: Context) {
    Toast.makeText(context, R.string.register_error_server_unavailable, Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
fun RegisterPreview() {
    ApelieTheme {
        RegisterScaffold(
            onBackPressed = {},
            onRegisterNewUser = {},
            state = RegisterState.Default,
            viewModel = getViewModel()
        )
    }
}
