package com.gustavohisan.apelieuser.main.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.gustavohisan.apelieuser.navigation.Destinations

internal data class HomeNavActions(val navController: NavHostController) {

    val onSectionClicked: (String) -> Unit = { sectionRoute ->
        navController.navigate(sectionRoute){
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val onStoreClicked: (Int) -> Unit = { storeId ->
        navController.navigate("${Destinations.STORE_ROUTE}/$storeId")
    }

    val onProductClicked: (Int) -> Unit = { itemId ->
        navController.navigate("${Destinations.PRODUCT_ROUTE}/$itemId")
    }

    val onOrderClicked: (Int) -> Unit = { orderId ->
        navController.navigate("${Destinations.ORDER_ROUTE}/$orderId")
    }

    val navigateToOrders: () -> Unit = {
        navController.navigate(Destinations.HomeSections.ORDERS.route)
    }

    val onRegisterAddressClicked: () -> Unit = {
        navController.navigate(Destinations.ADDRESS_ROUTE)
    }

    val onCheckoutClicked: () -> Unit = {
        navController.navigate(Destinations.CHECKOUT_ROUTE)
    }

    val onBackClicked: () -> Unit = {
        navController.navigateUp()
    }
}
