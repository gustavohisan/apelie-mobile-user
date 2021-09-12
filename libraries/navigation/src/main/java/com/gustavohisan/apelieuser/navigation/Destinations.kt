package com.gustavohisan.apelieuser.navigation

object Destinations {
    const val CHECKIN_ROUTE = "checkin"
    const val LOGIN_ROUTE = "login"
    const val REGISTER_ROUTE = "register"
    const val HOME_ROUTE = "home"

    enum class HomeSections(val route: String) {
        FEED("home/feed"),
        CART("home/cart"),
        ORDERS("home/orders"),
        PROFILE("home/profile")
    }
}
