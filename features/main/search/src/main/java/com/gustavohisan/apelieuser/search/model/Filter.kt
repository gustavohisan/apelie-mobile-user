package com.gustavohisan.apelieuser.search.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf

@Stable
internal class Filter(
    val name: String,
    enabled: Boolean = false
) {
    val enabled = mutableStateOf(enabled)
}
