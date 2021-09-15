package com.gustavohisan.apelieuser.login.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gustavohisan.apelieuser.design.ApelieTheme
import com.gustavohisan.apelieuser.login.R
import com.gustavohisan.apelieuser.login.model.LoginErrorType
import com.gustavohisan.apelieuser.login.model.LoginState
import org.koin.androidx.compose.getViewModel

@Composable
fun Login(onLoginValidated: () -> Unit, onRegisterNewUser: () -> Unit) {
    LoginLoader(onLoginValidated, onRegisterNewUser)
}

@Composable
private fun LoginLoader(
    onLoginValidated: () -> Unit,
    onRegisterNewUser: () -> Unit,
    viewModel: LoginViewModel = getViewModel()
) {
    val state: LoginState by viewModel.loginState.observeAsState(LoginState.Default)
    LoginScaffold(
        onLoginValidated = onLoginValidated,
        onRegisterNewUser = onRegisterNewUser,
        state = state,
        viewModel = viewModel
    )
}

@Composable
private fun LoginScaffold(
    onLoginValidated: () -> Unit,
    onRegisterNewUser: () -> Unit,
    state: LoginState,
    viewModel: LoginViewModel
) {
    val (email, setEmail) = rememberSaveable { mutableStateOf("") }
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }
    var isChecking by rememberSaveable { mutableStateOf(false) }
    var hasLoginError by rememberSaveable { mutableStateOf(false) }
    var hasPasswordError by rememberSaveable { mutableStateOf(false) }
    var loginErrorMessage by rememberSaveable { mutableStateOf("Erro") }
    var passwordErrorMessage by rememberSaveable { mutableStateOf("Erro") }

    when (state) {
        is LoginState.Success -> onLoginValidated()
        is LoginState.Error -> {
            state.errorTypeList.forEach { error ->
                when (error) {
                    LoginErrorType.INVALID_EMAIL -> {
                        hasLoginError = true
                        loginErrorMessage = stringResource(R.string.login_error_invalid_email)
                    }
                    LoginErrorType.INVALID_PASSWORD -> {
                        hasPasswordError = true
                        passwordErrorMessage = stringResource(R.string.login_error_invalid_password)
                    }
                    LoginErrorType.NOT_SUBSCRIBED -> {
                        hasLoginError = true
                        loginErrorMessage = stringResource(R.string.login_error_not_subscribed)
                    }
                    LoginErrorType.SERVER_UNAVAILABLE -> {
                        showToastError(LocalContext.current)
                    }
                    LoginErrorType.WRONG_PASSWORD -> {
                        hasPasswordError = true
                        passwordErrorMessage = stringResource(R.string.login_error_invalid_password)
                    }
                }
            }
            isChecking = false
        }
        is LoginState.Default -> {
            hasLoginError = false
            hasPasswordError = false
            isChecking = false
        }
        is LoginState.Checking -> {
            hasLoginError = false
            hasPasswordError = false
            isChecking = true
        }
    }
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier.padding(bottom = 30.dp),
                painter = painterResource(R.drawable.ic_apelie),
                contentDescription = "Apelie Logo"
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { value -> setEmail(value) },
                singleLine = true,
                label = { Text(stringResource(R.string.login_textinput_hint_email)) },
                isError = hasLoginError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasLoginError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = loginErrorMessage,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                value = password,
                onValueChange = { value -> setPassword(value) },
                singleLine = true,
                label = { Text(stringResource(R.string.login_textinput_hint_password)) },
                isError = hasPasswordError,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            if (hasPasswordError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = passwordErrorMessage,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
            Button(
                modifier = Modifier
                    .padding(bottom = 30.dp, top = 30.dp)
                    .fillMaxWidth(),
                onClick = { viewModel.validateUserLogin(email, password) },
                enabled = isChecking.not()
            ) {
                Text(
                    text = stringResource(R.string.login_button_submit),
                    fontWeight = FontWeight.Bold
                )
            }
            ClickableText(
                modifier = Modifier.padding(bottom = 30.dp),
                onClick = { onRegisterNewUser() },
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 12.sp,
                            color = colorResource(R.color.login_text_color)
                        )
                    ) {
                        append(stringResource(R.string.login_text_create_account))
                    }
                }
            )
            Text(
                text = stringResource(R.string.login_text_forgot_password),
                fontSize = 12.sp,
                color = colorResource(R.color.login_text_color)
            )
        }
    }
}

private fun showToastError(context: Context) {
    Toast.makeText(context, R.string.login_error_server_unavailable, Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
fun LoginPreview() {
    ApelieTheme {
        LoginScaffold(
            onLoginValidated = {},
            onRegisterNewUser = {},
            state = LoginState.Default,
            viewModel = getViewModel()
        )
    }
}
