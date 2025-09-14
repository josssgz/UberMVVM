package com.example.urber

import android.graphics.drawable.Icon
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
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.w3c.dom.Text

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
            AccountBody()
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
                w = 110.dp,
                h = 100.dp,
                imageVector = Icons.Default.Call  ,
                text = "Ajuda"
            )
            AccountSmallCards(
                w = 110.dp,
                h = 100.dp,
                imageVector = Icons.Default.Menu  ,
                text = "Carteira"
            )
            AccountSmallCards(
                w = 110.dp,
                h = 100.dp,
                imageVector = Icons.Default.Email  ,
                text = "Mensagem"
            )
        }
        Spacer(
            modifier = Modifier
                .padding(6.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            AccountLargeCards(
                imageVector = Icons.Default.Email,
                title = "Ride Pass",
                subtitle = "Save on Routine Rides"
            )

        }
        Spacer(
            modifier = Modifier
                .padding(6.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            AccountLargeCards(
                imageVector = Icons.Default.Email,
                title = "Try Uber One free",
                subtitle ="Unlock 10% Uber One credits on rides \nand more"
            )

        }
        Spacer(
            modifier = Modifier
                .padding(6.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            AccountLargeCards(
                imageVector = Icons.Default.Email,
                        title = "Safety Checkup",
                        subtitle ="Learn ways to make rides safer"
            )

        }
        Spacer(
            modifier = Modifier
                .padding(6.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            AccountLargeCards(
                imageVector = Icons.Default.Email,
                title = "Estimated CO2 saved",
                subtitle = ""
            )

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
            Text(text = text)
        }
    }
}
@Composable
fun AccountLargeCards(title: String, subtitle: String, imageVector: ImageVector){
    Card (
        modifier = Modifier
            .fillMaxSize(),
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
                Text(text = title)
                Text(text = subtitle)
            }
            Icon(
                imageVector = imageVector,
                contentDescription = title,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}