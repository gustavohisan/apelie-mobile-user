package com.gustavohisan.apelieuser.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.gustavohisan.apelieuser.main.provider.FeedProvider
import com.gustavohisan.apelieuser.main.provider.StoreProvider
import com.gustavohisan.apelieuser.navigation.Destinations
import org.koin.androidx.compose.get

@Composable
internal fun MainScreenNavGraph(
    navController: NavHostController,
    actions: HomeNavActions,
    paddings: PaddingValues,
    startDestination: String = Destinations.HOME_ROUTE,
    feedProvider: FeedProvider = get(),
    storeProvider: StoreProvider = get()
) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Destinations.HOME_ROUTE,
            startDestination = Destinations.HomeSections.FEED.route
        ) {
            composable(Destinations.HomeSections.FEED.route) {
                Scaffold(modifier = Modifier.padding(paddings)) {
                    feedProvider.FeedComposable(actions.onStoreClicked)
                }
            }
            composable(Destinations.HomeSections.CART.route) {
                Scaffold {
                    Text("CART")
                }
            }
            composable(Destinations.HomeSections.ORDERS.route) {
                Scaffold {
                    Text("ORDERS")
                }
            }
            composable(Destinations.HomeSections.PROFILE.route) {
                Scaffold {
                    Text("PROFILE")
                }
            }
        }

        composable(
            route = "${Destinations.STORE_ROUTE}/{${Destinations.STORE_ID}}",
            arguments = listOf(navArgument(Destinations.STORE_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val storeId = arguments.getInt(Destinations.STORE_ID, 0)
            storeProvider.StoreComposable(
                storeId = storeId,
                onProductClicked = actions.onProductClicked,
                onBackClicked = actions.onBackClicked
            )
        }

        composable(
            route = "${Destinations.PRODUCT_ROUTE}/{${Destinations.PRODUCT_ID}}",
            arguments = listOf(navArgument(Destinations.PRODUCT_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val storeId = arguments.getInt(Destinations.PRODUCT_ID, 0)
            Scaffold {
                Text("Store id $storeId")
            }
        }
    }
}

