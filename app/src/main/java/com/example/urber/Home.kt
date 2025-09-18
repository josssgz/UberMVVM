package com.example.urber

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
fun HomeBody(){

    Column {

        HomeSuggestions()
        HomeCardsWText()
    }

}

data class CardsWText(
    val title: String,
    val subtitle: String,
    val image: Int
)

@Composable
fun HomeCardsWText(){

    CreateTitle("Save every day")

    val CardsWTextContent1 = listOf(
        CardsWText(
            title = "Add a stop or 5 ->",
            subtitle = "Pick up something along the way",
            image = R.drawable.image
        ),
        CardsWText(
            title = "Uber Moto rides ->",
            subtitle = "Affordable motorcycle pickups",
            image = R.drawable.image
        )
    )

    LazyRow (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(CardsWTextContent1) { CardsWText ->
            CreateHomeCardsWText(
                title = CardsWText.title,
                subtitle = CardsWText.subtitle,
                image = painterResource(id = CardsWText.image)
            )
        }
    }

    CreateTitle("Go on 2 wheels")

    val CardsWTextContent2 = listOf(
        CardsWText(
            title = "Easy way to save ->",
            subtitle = "Get going for less",
            image = R.drawable.image
        ),
        CardsWText(
            title = "Try 2 wheels ->",
            subtitle = "Save, beat traffic, go green",
            image = R.drawable.image
        ),
        CardsWText(
            title = "Fewer emissions ->",
            subtitle = "A more sustainable ride",
            image = R.drawable.image
        )
    )

    LazyRow (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(CardsWTextContent2) { CardsWText ->
            CreateHomeCardsWText(
                title = CardsWText.title,
                subtitle = CardsWText.subtitle,
                image = painterResource(id = CardsWText.image)
            )
        }
    }

    CreateTitle("Save yourself a trip")

    val CardsWTextContent3 = listOf(
        CardsWText(
            title = "Send a gift ->",
            subtitle = "Get same-day delivery on gifts",
            image = R.drawable.image
        ),
        CardsWText(
            title = "Need an iten back? ->",
            subtitle = "Get left-behind items delivered",
            image = R.drawable.image
        ),
        CardsWText(
            title = "Send items safely ->",
            subtitle = "Send, track, and get notified",
            image = R.drawable.image
        )
    )

    LazyRow (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(CardsWTextContent3) { CardsWText ->
            CreateHomeCardsWText(
                title = CardsWText.title,
                subtitle = CardsWText.subtitle,
                image = painterResource(id = CardsWText.image)
            )
        }
    }

}

@Composable
fun CreateHomeCardsWText(title: String, subtitle: String, image: Painter){

    Column (
        modifier = Modifier
            .padding(start = 14.dp)
            .padding(bottom = 14.dp)
    ) {

        Column (
            modifier = Modifier
                .padding(vertical = 7.dp)
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp, 130.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(vertical = 1.dp))
            Text(
                text = subtitle,
                color = Color.Gray
            )
        }

    }

}

@Composable
fun CreateTitle(title: String){
    Column (
        modifier = Modifier
            .padding(start = 14.dp)
            .padding(top = 14.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

data class CardContent(
    val color: Color,
    val text: String,
    val buttonText: String,
    val image: Int
)

@Composable
fun HomeSuggestions(){

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
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ServiceSmallCards(
            w = 83.dp,
            h = 90.dp,
            image =painterResource(id = R.drawable.motoicon),
            imgDescription = "MotoIcon",
            imgText = "Moto"
        )

        ServiceSmallCards(
            w = 83.dp,
            h = 90.dp,
            image = painterResource(id = R.drawable.twowheelsicon),
            imgDescription = "2wheelsIcon",
            imgText = "2-Wheels"
        )

        ServiceSmallCards(
            w = 83.dp,
            h = 90.dp,
            image = painterResource(id = R.drawable.senioricon),
            imgDescription = "seniorIcon",
            imgText = "Seniors"
        )

        ServiceSmallCards(
            w = 83.dp,
            h = 90.dp,
            image = painterResource(id = R.drawable.teenicon),
            imgDescription = "teenIcon",
            imgText = "Teens"
        )
    }

    val CardsContents = listOf(
        CardContent(
            color = Color.Gray,
            text = "Enjoy a little more room with Uber Comfort",
            buttonText = "Try Comfort",
            image = R.drawable.image
        ),
        CardContent(
            color = Color.Magenta,
            text = "Luxurious rides await, choo Uber Black",
            buttonText = "Ride Uber Black",
            image = R.drawable.image
        ),
        CardContent(
            color = Color.Blue,
            text = "Ready? Then let's roll.",
            buttonText = "Ride with Uber",
            image = R.drawable.image
        )
    )

    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(CardsContents) { CardContent ->
            CreateCardsWContent(
                color = CardContent.color,
                text = CardContent.text,
                buttonText = CardContent.buttonText,
                image = painterResource(id = CardContent.image)
            )
        }
    }

}

@Composable
fun CreateCardsWContent(color: Color, text: String, buttonText: String, image: Painter){

    Row (
        modifier = Modifier
            .size(365.dp, 160.dp)
            .clip(RoundedCornerShape(12.dp))
    ){
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .width(240.dp)
                .background(color),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 15.dp)
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = text,
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
                Button(
                    onClick = { },
                    modifier = Modifier
                        .height(60.dp)
                        .wrapContentWidth()
                        .padding(vertical = 13.dp)
                        .padding(horizontal = 15.dp),
                    colors = ButtonDefaults.buttonColors(Color.DarkGray)
                ) {
                    Text(
                        text = buttonText,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

        }

        Column (
            modifier = Modifier
                .fillMaxSize()

        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
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
            text = "Uber",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold
        )
    }
}