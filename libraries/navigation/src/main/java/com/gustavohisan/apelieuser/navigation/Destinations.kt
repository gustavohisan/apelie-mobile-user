package com.gustavohisan.apelieuser.navigation

object Destinations {
    const val CHECKIN_ROUTE = "checkin"
    const val LOGIN_ROUTE = "login"
    const val REGISTER_ROUTE = "register"
    const val HOME_ROUTE = "home"
    const val STORE_ROUTE = "home/feed/store"
    const val STORE_ID = "storeId"
    const val PRODUCT_ROUTE = "home/feed/store/product"
    const val PRODUCT_ID = "productId"
    const val ORDER_ROUTE = "home/feed/orders/order"
    const val ORDER_ID = "orderId"
    const val ADDRESS_ROUTE = "home/address"
    const val CHECKOUT_ROUTE = "home/cart/checkout"

    enum class HomeSections(val route: String) {
        FEED("home/feed"),
        SEARCH("home/search"),
        CART("home/cart"),
        ORDERS("home/orders"),
        PROFILE("home/profile")
    }
}
