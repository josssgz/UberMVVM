package com.example.urber

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview (showBackground = true)
@Composable
fun ActivityScreen(){
    Scaffold (
        topBar = { HeaderActivity() },
        bottomBar = { Footer() }
    ) {
            innerPadding ->

        Column (
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            BodyActivity()
        }

    }
}
//@Preview (showBackground = true)
@Composable
fun HeaderActivity(){
    Row (modifier =  Modifier.fillMaxWidth()
        .height(70.dp)
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            "Activity", style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

//@Preview (showBackground = true)
@Composable
fun BodyActivity(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row ( modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                "Upcoming" , style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Row (
            modifier = Modifier
                .fillMaxHeight()
        ){  }
    }
}