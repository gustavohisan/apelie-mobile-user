package com.gustavohisan.apelieuser.main.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.gustavohisan.apelieuser.main.R
import com.gustavohisan.apelieuser.navigation.Destinations

internal sealed class HomeSections(
    val route: String,
    val icon: ImageVector,
    @StringRes val label: Int
) {

    object Feed : HomeSections(
        Destinations.HomeSections.FEED.route,
        Icons.Default.Home,
        R.string.feed_section_text
    )

    object Search : HomeSections(
        Destinations.HomeSections.SEARCH.route,
        Icons.Default.Search,
        R.string.search_section_text
    )

    object Cart : HomeSections(
        Destinations.HomeSections.CART.route,
        Icons.Default.ShoppingCart,
        R.string.cart_section_text
    )

    object Orders : HomeSections(
        Destinations.HomeSections.ORDERS.route,
        Icons.Default.ReceiptLong,
        R.string.orders_section_text
    )

    object Profile : HomeSections(
        Destinations.HomeSections.PROFILE.route,
        Icons.Default.Person,
        R.string.profile_section_text
    )
}
