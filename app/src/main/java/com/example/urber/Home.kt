package com.example.urber

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview (showBackground = true)
@Composable
fun HomeScreen(){
    Scaffold (
        topBar = { HomeHeader() },
        bottomBar = { Footer() }
    ) {
            innerPadding ->

        Column (
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            HomeBody()
        }

    }
}
@Composable
fun HomeHeader(){
    Row (modifier =  Modifier.fillMaxWidth()
        .height(70.dp)
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            "Uber", style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun HomeBody(){

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .padding(bottom = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "Suggestions",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "See all",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
    }

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



}

@Preview (showBackground = true)
@Composable
fun CreateCardsWContent(){

    Card (
        modifier = Modifier
            .size(360.dp, 160.dp),
        colors = CardDefaults.cardColors(Color.Red),
        shape = RoundedCornerShape(8.dp)
    ){

        Column (
            modifier = Modifier
                .fillMaxHeight()
                .width(240.dp)
        ) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Reserve a ride ahead for peace of mind",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Card (
                    modifier = Modifier
                        .size(100.dp, 30.dp),
                    colors = CardDefaults.cardColors(Color.DarkGray),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = "buttonText"
                    )
                }
            }

        }

        Column {  }

    }

}
