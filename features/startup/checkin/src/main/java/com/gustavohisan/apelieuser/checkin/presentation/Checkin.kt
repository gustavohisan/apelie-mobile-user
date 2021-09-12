package com.gustavohisan.apelieuser.checkin.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gustavohisan.apelieuser.checkin.state.CheckinState
import com.gustavohisan.apelieuser.design.ApelieTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun Checkin(onLoginValidated: () -> Unit, onLoginNotAvailable: () -> Unit) {
    CheckinLoader(onLoginValidated, onLoginNotAvailable)
}

@Composable
private fun CheckinLoader(
    onLoginValidated: () -> Unit,
    onLoginNotAvailable: () -> Unit,
    viewModel: CheckinViewModel = getViewModel()
) {
    viewModel.getIfUserIsLoggedIn()
    val state: CheckinState by viewModel.isLoginValid.observeAsState(CheckinState.Loading)
    CheckinScaffold(onLoginValidated, onLoginNotAvailable, state)
}

@Composable
private fun CheckinScaffold(
    onLoginValidated: () -> Unit,
    onLoginNotAvailable: () -> Unit,
    state: CheckinState
) {
    Scaffold {
        Crossfade(state) { state ->
            when (state) {
                is CheckinState.Loading -> CheckingLoading()
                is CheckinState.LoggedIn -> onLoginValidated()
                is CheckinState.NotLogged -> onLoginNotAvailable()
            }
        }
    }
}

@Composable
private fun CheckingLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun CheckinPreview() {
    ApelieTheme {
        CheckinScaffold(
            onLoginValidated = {},
            onLoginNotAvailable = {},
            state = CheckinState.Loading
        )
    }
}
