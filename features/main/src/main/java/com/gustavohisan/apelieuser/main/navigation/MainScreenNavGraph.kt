package com.gustavohisan.apelieuser.main.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gustavohisan.apelieuser.navigation.Destinations

@Composable
internal fun MainScreenNavGraph(
    navController: NavHostController,
    actions: HomeNavActions,
    startDestination: String = Destinations.HOME_ROUTE
) {

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Destinations.HOME_ROUTE,
            startDestination = Destinations.HomeSections.FEED.route
        ) {
            composable(Destinations.HomeSections.FEED.route) {
                Scaffold() {
                    Text("teste1")
                }
            }
            composable(Destinations.HomeSections.CART.route) {
                Scaffold() {
                    Text("teste2")
                }
            }
            composable(Destinations.HomeSections.ORDERS.route) {
                Scaffold() {
                    Text("teste3")
                }
            }
            composable(Destinations.HomeSections.PROFILE.route) {
                Scaffold() {
                    Text("teste4")
                }
            }
        }
    }
}
