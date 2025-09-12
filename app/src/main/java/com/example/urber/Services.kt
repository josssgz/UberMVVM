package com.example.urber

import android.media.Image
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview (showBackground = true)
@Composable
fun ServicesScreen(){

    Scaffold (
        topBar = { ServiceHeader() },
        bottomBar = { Footer() }
    ) {
        innerPadding ->

        Column (
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ServicesContent()
        }

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
                style = MaterialTheme.typography.titleLarge,
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

            ServiceSmallCards(
                83.dp,
                90.dp,
                painterResource(id = R.drawable.reserveicon),
                "reserveIcon",
                "Reserve"
            )

            ServiceSmallCards(
                83.dp,
                90.dp,
                painterResource(id = R.drawable.twowheelsicon),
                "2wheelsIcon",
                "2-Wheels"
            )

            ServiceSmallCards(
                83.dp,
                90.dp,
                painterResource(id = R.drawable.senioricon),
                "seniorIcon",
                "Seniors"
            )

            ServiceSmallCards(
                83.dp,
                90.dp,
                painterResource(id = R.drawable.teenicon),
                "teenIcon",
                "Teens"
            )

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
                style = MaterialTheme.typography.titleLarge,
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
            .padding(top = 15.dp)
    ) {
        Text(
            text = "Services",
            style = MaterialTheme.typography.displaySmall,
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
fun ServiceSmallCards(w: Dp, h: Dp, image: Painter, imgDescription: String, imgText: String){

    Card (
        modifier = Modifier
            .size(w, h),
        colors = CardDefaults.cardColors(Color.LightGray),
        shape = RoundedCornerShape(8.dp),

    ) {

        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = imgDescription,
                modifier = Modifier
                    .size(40.dp)
            )

            Text(
                text = imgText,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(5.dp)
            )

        }
    }

}


