package com.example.urber

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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//@Preview (showBackground = true)
@Composable
fun AccountScreen(navController: NavController){
    Scaffold (
        topBar = { AccountHeader() }
    ) {
        innerPadding ->

        Column (
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            AccountBody()
        }

    }
}

@Composable
fun AccountBody(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically
        )
        {
            AccountSmallCards(
                w = 115.dp,
                h = 100.dp,
                imageVector = Icons.Default.Call  ,
                text = "Ajuda"
            )
            AccountSmallCards(
                w = 115.dp,
                h = 100.dp,
                imageVector = Icons.Default.Menu  ,
                text = "Carteira"
            )
            AccountSmallCards(
                w = 115.dp,
                h = 100.dp,
                imageVector = Icons.Default.Email  ,
                text = "Mensagem"
            )
        }
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            AccountLargeCards(
                title = "Ride Pass",
                subtitle = "Save on Routine Rides",
                painterResource(id = R.drawable.pass)
            )

        }
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            AccountLargeCards(
                title = "Try Uber One free",
                subtitle ="Unlock 10% Uber One credits on rides \nand more",
                painterResource(id = R.drawable.car)
            )

        }
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            AccountLargeCards(
                title = "Safety Checkup",
                subtitle ="Learn ways to make rides safer",
                painterResource(id = R.drawable.load)
            )

        }
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            AccountLargeCards(
                title = "Estimated CO2 saved",
                subtitle = null,
                painterResource(id = R.drawable.folha)
            )

        }
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        AccountDownRows(
            painterResource(id = R.drawable.family),
            title = " Family and Teens",
            subtitle = "Teen and adult accounts"
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        AccountDownRows(
            painterResource(id = R.drawable.gear),
            title = " Settings",

            subtitle = null
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        AccountDownRows(
            painterResource(id = R.drawable.phone),
            title = " Simple mode",
            subtitle = "A simplied app for older adults"
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        AccountDownRows(
            painterResource(id = R.drawable.gift),
            title = " Send a gift",
            subtitle = null
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        AccountDownRows(
            painterResource(id = R.drawable.driver),
            title = " Earn by driving or delivering",
            subtitle = null
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        AccountDownRows(
            painterResource(id = R.drawable.group),
            title = " Saved groups",
            subtitle = null
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        AccountDownRows(
            painterResource(id = R.drawable.business),
            title = " Set up your business profile",
            subtitle = "Automate work travel & meal expenses"
        )

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        AccountDownRows(
            painterResource(id = R.drawable.trophy),
            title = " Partner Rewards",
            subtitle = null
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        AccountDownRows(
            painterResource(id = R.drawable.group),
            title = " Refer friends, unlock deals",
            subtitle = null
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        AccountDownRows(
            painterResource(id = R.drawable.user),
            title = " Menage Uber account",
            subtitle = null
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        AccountDownRows(
            painterResource(id = R.drawable.legal),
            title = " Legal",
            subtitle = null
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("v1.0000000")
        }


    }
}
@Composable
fun AccountSmallCards(w:Dp, h: Dp, imageVector: ImageVector, text: String){
    Card (
        modifier = Modifier
            .size(width = w, height = h),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        shape = RoundedCornerShape(8.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = text,
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun AccountLargeCards(title: String, subtitle: String?, image: Painter){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)

    ){
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                if (subtitle != null && subtitle.isNotEmpty()) {
                    Text(
                        text = subtitle
                    )
                }
            }
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}
@Composable
fun AccountDownRows(image: Painter, title: String, subtitle: String?){
    Row (
        modifier = Modifier
            .fillMaxSize()
            .height(50.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            modifier = Modifier
                .size(30.dp),
            painter = image,
            contentDescription = title
        )
        Spacer(
            modifier = Modifier
                .width(20.dp)
        )
        Column (
            modifier = Modifier,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            if (subtitle != null && subtitle.isNotEmpty()) {
                Text(
                    text = subtitle
                )
            }
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
                Text("José Farah", style = MaterialTheme.typography.displayMedium
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
                                fontWeight = FontWeight.Bold,
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
                            fontWeight = FontWeight.Bold,
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