package com.gustavohisan.apelieuser.main.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gustavohisan.apelieuser.main.navigation.HomeNavActions
import com.gustavohisan.apelieuser.main.navigation.HomeSections
import com.gustavohisan.apelieuser.main.navigation.MainScreenNavGraph

@Composable
fun Home() {
    HomeLoader()
}

@Composable
private fun HomeLoader() {
    val navController = rememberNavController()
    val actions = remember(navController) { HomeNavActions(navController) }
    val sections =
        listOf(HomeSections.Feed, HomeSections.Cart, HomeSections.Orders, HomeSections.Profile)
    HomeScaffold(
        navController = navController,
        actions = actions,
        onSectionClicked = actions.onSectionClicked,
        currentRoute = currentRoute(navController),
        sections = sections
    )
}

@Composable
internal fun HomeScaffold(
    navController: NavHostController,
    actions: HomeNavActions,
    onSectionClicked: (String) -> Unit,
    currentRoute: String?,
    sections: List<HomeSections>
) {
    Scaffold(
        bottomBar = {
            MainBottomBar(
                onSectionClicked = onSectionClicked,
                sections = sections,
                currentRoute = currentRoute
            )
        }
    ) {
        MainScreenNavGraph(navController = navController, actions = actions)
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}