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

    val onBackClicked: () -> Unit = {
        navController.navigateUp()
    }
}
