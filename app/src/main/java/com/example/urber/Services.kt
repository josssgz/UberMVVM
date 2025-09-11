package com.example.urber

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview (showBackground = true)
@Composable
fun ServicesScreen(){

    Column {
        ServiceHeader()
        ServicesContent()
        Footer()
    }

}


@Composable
fun ServicesContent(){

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .padding(bottom = 15.dp)
        ) {
            Text(
                text = "Go anywhere, get anything",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            repeat(2){
                ServiceLargeCards(180.dp, 90.dp)
            }
        }

        Spacer(
            modifier = Modifier
                .padding(vertical = 6.dp)
        )

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(4){
                ServiceSmallCards(83.dp, 90.dp)
            }
        }

        Spacer(
            modifier = Modifier
                .padding(vertical = 8.dp)
        )

        HorizontalDivider(thickness = 2.dp)

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Text(
                text = "Get Courier to help",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(2){
                ServiceLargeCards(180.dp, 90.dp)
            }
        }

    }



}

@Composable
fun ServiceHeader(){

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = "Services",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun ServiceLargeCards(w: Dp, h: Dp){

    Card (
        modifier = Modifier
            .size(w, h),
        colors = CardDefaults.cardColors(Color.LightGray),
        shape = RoundedCornerShape(8.dp)
    ) {  }

}

@Composable
fun ServiceSmallCards(w: Dp, h: Dp){

    Card (
        modifier = Modifier
            .size(w, h),
        colors = CardDefaults.cardColors(Color.LightGray),
        shape = RoundedCornerShape(8.dp)
    ) {  }

}

