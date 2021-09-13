package com.gustavohisan.apelieuser.navigation

import androidx.navigation.NavHostController

internal data class NavActions(val navController: NavHostController) {

    val openMainScreen: () -> Unit = {
        navController.navigate(Destinations.HOME_ROUTE) {
            popUpTo(0)
        }
    }

    val openLoginScreen: () -> Unit = {
        navController.navigate(Destinations.LOGIN_ROUTE) {
            popUpTo(0)
        }
    }

    val openRegisterScreen: () -> Unit = {
        navController.navigate(Destinations.REGISTER_ROUTE)
    }
}
