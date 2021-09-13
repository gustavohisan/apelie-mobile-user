package com.gustavohisan.apelieuser.main.navigation

import androidx.navigation.NavHostController

internal data class HomeNavActions(val navController: NavHostController) {

    val onSectionClicked: (String) -> Unit = { sectionRoute ->
        navController.navigate(sectionRoute)
    }
}
