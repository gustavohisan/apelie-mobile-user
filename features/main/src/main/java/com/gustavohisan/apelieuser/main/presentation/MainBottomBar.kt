package com.gustavohisan.apelieuser.main.presentation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gustavohisan.apelieuser.design.mainBlue
import com.gustavohisan.apelieuser.design.mainGrey
import com.gustavohisan.apelieuser.main.navigation.HomeSections

@Composable
internal fun MainBottomBar(
    onSectionClicked: (String) -> Unit,
    sections: List<HomeSections>,
    currentRoute: String?
) {
    val sectionsValues = remember { sections.map { section -> section.route } }
    if (currentRoute in sectionsValues) {
        BottomNavigation(
            backgroundColor = Color.White
        ) {
            sections.forEach { section ->
                val selected = section.route == currentRoute
                val color = if (selected) mainBlue else mainGrey
                BottomNavigationItem(
                    icon = { Icon(section.icon, stringResource(id = section.label), tint = color) },
                    label = { Text(stringResource(id = section.label), color = color) },
                    selected = selected,
                    alwaysShowLabel = false,
                    onClick = {
                        if (selected.not()) {
                            onSectionClicked(section.route)
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun MainBottomBarPreview() {
    MainBottomBar(
        onSectionClicked = {},
        sections = listOf(HomeSections.Cart, HomeSections.Feed),
        currentRoute = HomeSections.Feed.route
    )
}
