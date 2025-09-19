package com.example.urber

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//@Preview (showBackground = true)
@Composable
fun ServicesScreen(navController: NavController){

    Scaffold (
        topBar = { ServiceHeader() }
    ) {
        innerPadding ->

        Column (
            modifier = Modifier
                .padding(innerPadding)
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
            ServiceLargeCards(
                180.dp,
                90.dp,
                painterResource(id = R.drawable.caricon),
                "RideIcon",
                "Ride"
            )
            ServiceLargeCards(
                180.dp,
                90.dp,
                painterResource(id = R.drawable.reserveicon),
                "ReserveIcon",
                "Reserve"
            )
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
                painterResource(id = R.drawable.motoicon),
                "MotoIcon",
                "Moto"
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
            ServiceLargeCards(
                180.dp,
                90.dp,
                painterResource(id = R.drawable.caixaicon),
                "SendIcon",
                "Send"
            )
            ServiceLargeCards(
                180.dp,
                90.dp,
                painterResource(id = R.drawable.caixasicon),
                "FlashNacionalIcon",
                "Flash Nacional"
            )
        }

    }

}

@Composable
fun ServiceHeader(){

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .padding(top = 20.dp)
    ) {
        Text(
            text = "Services",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun ServiceLargeCards(w: Dp, h: Dp, image: Painter, imgDescription: String, imgText: String){

    Card (
        modifier = Modifier
            .size(w, h),
        colors = CardDefaults.cardColors(Color.LightGray),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 5.dp),
        ) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = image,
                    contentDescription = imgDescription,
                    modifier = Modifier
                        .size(50.dp)
                )
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = imgText,
                    style = MaterialTheme.typography.labelLarge
                )
            }

        }
    }

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


