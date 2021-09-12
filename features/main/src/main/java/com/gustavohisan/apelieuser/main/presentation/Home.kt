//package com.gustavohisan.apelieuser.main.presentation
//
//import androidx.compose.material.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.navigation.NavBackStackEntry
//import androidx.navigation.NavController
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import com.gustavohisan.apelieuser.navigation.Destinations
//
//@Composable
//fun home(){
//    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(navController, bottomNavigationItems)
//        },
//    ) {
//        MainScreenNavigationConfigurations(navController)
//    }
//}
//
//fun NavGraphBuilder.addHomeGraph(
//    onTabSelected: (Long, NavBackStackEntry) -> Unit
//) {
//    composable(Destinations.HomeSections.FEED.route) { from ->
//        Scaffold() {}
//    }
//    composable(Destinations.HomeSections.CART.route) {
//        Scaffold() {}
//    }
//    composable(Destinations.HomeSections.ORDERS.route) {
//        Scaffold() {}
//    }
//    composable(Destinations.HomeSections.PROFILE.route) {
//        Scaffold() {}
//    }
//}
//
//@Composable
//fun BottomNavigationBar(
//    navController: NavController,
//    tabs: Array<Destinations.HomeSections>
//) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    val sections = remember { Destinations.HomeSections.values() }
//    val routes = remember { sections.map { it.route } }
//    if (currentRoute in routes) {
//        val currentSection = sections.first { it.route == currentRoute }
//    }
//}
//
//@Composable
//private fun mainBottomNavLayout(
//    selectedIndex: Int,
//    itemCount: Int
//) {
//
//}
