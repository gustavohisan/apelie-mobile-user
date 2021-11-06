package com.gustavohisan.apelieuser.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.gustavohisan.apelieuser.design.textGrey

@Composable
internal fun Profile() {
    ProfileLoader()
}

@Composable
private fun ProfileLoader() {
    ProfileScaffold()
}

@Composable
private fun ProfileScaffold() {
    val scrollState = rememberScrollState()
    Scaffold {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape),
                    colorFilter = ColorFilter.tint(textGrey)
                )
                Text(text = "Alterar foto")
            }
            Row(modifier = Modifier
                .clickable { }
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Filled.Assignment,
                    contentDescription = null,
                    tint = textGrey
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Minhas informações", color = textGrey)
            }
            Row(modifier = Modifier
                .clickable { }
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Filled.House, contentDescription = null, tint = textGrey)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Meus endereços", color = textGrey)
            }
            Row(modifier = Modifier
                .clickable { }
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Filled.CreditCard,
                    contentDescription = null,
                    tint = textGrey
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Meus métodos de pagamento", color = textGrey)
            }
            Row(modifier = Modifier
                .clickable { }
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    tint = textGrey
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("Configurações", color = textGrey)
            }
            Row(modifier = Modifier
                .clickable { }
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Filled.Help, contentDescription = null, tint = textGrey)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Ajuda", color = textGrey)
            }
            Row(modifier = Modifier
                .clickable { }
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Filled.Logout, contentDescription = null, tint = textGrey)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Logout", color = textGrey)
            }
            Row(modifier = Modifier
                .clickable { }
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Filled.Article, contentDescription = null, tint = textGrey)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Termos de uso", color = textGrey)
            }
        }
    }
}
