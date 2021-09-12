package com.gustavohisan.apelieuser.design

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ApelieTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = colors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}
