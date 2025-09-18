package com.example.urber

import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun BodyActivity(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
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

    }
}

@Composable
fun ActivityCard(title: String, subtitle: String, image: Int){
    Card (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent), border = BorderStroke(1.dp, Color.Gray)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically

        ){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center
            ){
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Text(text = subtitle)
        }

                Image(
                    painter = painterResource(id = image),
                    contentDescription = title,
                    modifier = Modifier.size(50.dp)
                )
             }
    }
}

//@Preview (showBackground = true)
@Composable
fun HeaderActivity(){
    Card (
        modifier = Modifier
            .padding(top = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent),
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Activity", style = MaterialTheme.typography.displayMedium
                        .copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

