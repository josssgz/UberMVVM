package com.example.urber.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//@Preview(showBackground = true)
@Composable
fun Footer(navController: NavController, currentRoute: String){

    Row (
        modifier = Modifier
            .height(75.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){

        CreateFooterOption(
            Icons.Default.Home,
            "home",
            "Home",
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") }
        )

        CreateFooterOption(
            Icons.Default.Menu,
            "services",
            "Services",
            selected = currentRoute == "services",
            onClick = { navController.navigate("services") }
        )

        CreateFooterOption(
            Icons.Default.DateRange,
            "activity",
            "Activity",
            selected = currentRoute == "activity",
            onClick = { navController.navigate("activity") }
        )

        CreateFooterOption(
            Icons.Default.Person,
            "account",
            "Account",
            selected = currentRoute == "account",
            onClick = { navController.navigate("account") }
        )

    }
}

@Composable
fun CreateFooterOption(icon: ImageVector, description: String, text: String, selected: Boolean, onClick: () -> Unit){

    val color = if (selected) MaterialTheme.colorScheme.primary else Color.DarkGray

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onClick() }
            .padding(4.dp)
    ) {

        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier
                .size(45.dp),
            tint = color
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = color
        )

    }

}