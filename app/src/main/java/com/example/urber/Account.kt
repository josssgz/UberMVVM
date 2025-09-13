package com.example.urber

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview (showBackground = true)
@Composable
fun AccountScreen(){
    Scaffold (
        topBar = { AccountHeader() },
        bottomBar = { Footer() }
    ) {
            innerPadding ->

        Column (
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

        }

    }
}
@Composable
fun AccountHeader(){
    Card (
        modifier = Modifier
            .padding(15.dp),
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
            Column (
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("José Farah", style = MaterialTheme.typography.titleLarge
                    .copy(fontWeight = FontWeight.Bold))
                Row {
                    Card(
                        shape = RoundedCornerShape(3.dp),
                        colors = CardDefaults.cardColors(Color.DarkGray)
                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 2.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp),
                                tint = Color.Yellow
                            )
                            Text(
                                text = "4.88",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(horizontal = 2.dp)
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .width(15.dp)
                    )
                    Card(
                        shape = RoundedCornerShape(3.dp),
                        colors = CardDefaults.cardColors(Color.DarkGray)
                    ) {
                        Text("Not verified",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(horizontal = 2.dp)
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .width(13.dp)
            )
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
            )
        }
    }
}