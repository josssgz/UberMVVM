package com.example.urber

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun Footer(){

    Row (
        modifier = Modifier
            .height(75.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){

        CreateFooterOption(Icons.Default.Home, "home", "Home")
        CreateFooterOption(Icons.Default.Menu, "services", "Services")
        CreateFooterOption(Icons.Default.DateRange, "activity", "Activity")
        CreateFooterOption(Icons.Default.Person, "account", "Account")

    }

}

@Composable
fun CreateFooterOption(icon: ImageVector, description: String, text: String){

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier
                .size(30.dp)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )

    }

}