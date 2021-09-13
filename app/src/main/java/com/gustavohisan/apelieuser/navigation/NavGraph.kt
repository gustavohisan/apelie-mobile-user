package com.gustavohisan.apelieuser.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gustavohisan.apelieuser.checkin.presentation.Checkin
import com.gustavohisan.apelieuser.login.presentation.Login
import com.gustavohisan.apelieuser.main.presentation.Home
import com.gustavohisan.apelieuser.register.presentation.Register

@Composable
fun NavGraph(startDestination: String = Destinations.CHECKIN_ROUTE) {
    val navController = rememberNavController()
    val actions = remember(navController) { NavActions(navController) }

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Destinations.CHECKIN_ROUTE) {
            Checkin(
                onLoginValidated = actions.openMainScreen,
                onLoginNotAvailable = actions.openLoginScreen
            )
        }

        composable(Destinations.LOGIN_ROUTE) {
            Login(
                onLoginValidated = actions.openMainScreen,
                onRegisterNewUser = actions.openRegisterScreen
            )
        }

        composable(Destinations.REGISTER_ROUTE) {
            Register(
                onBackPressed = actions.openLoginScreen,
                onRegisterNewUser = actions.openMainScreen
            )
        }

        composable(Destinations.HOME_ROUTE) {
            Home()
        }
    }
}
