package com.example.urber

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
@Composable
fun ActivityScreen(navController: NavHostController) {

    val context = LocalContext.current
    val placesDAO = remember {
        AppDatabase.getDatabase(context).placesDAO()
    }
    val savedPlaces by placesDAO.getAllPlaces().collectAsStateWithLifecycle(initialValue = emptyList())

    Scaffold(
            topBar = { HeaderActivity() },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                BodyActivity()

                if (savedPlaces.isNotEmpty()) {
                    Text(
                        text = "Locais Favoritos",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                    )

                    // Usamos um LazyColumn para exibir a lista, mas precisamos gerenciar sua altura
                    // já que está dentro de uma Column com verticalScroll.
                    LazyColumn(
                        modifier = Modifier
                            .height(500.dp) // Altura fixa ou calculada é essencial
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        userScrollEnabled = false // Desliga o scroll do LazyColumn se a Column externa já tem
                    ) {
                        items(savedPlaces) { place ->
                            // Exibe os itens de forma simples
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Default.Place, contentDescription = null, tint = Color.Gray)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = place.desc)
                            }
                        }
                    }
                } else {
                    Text(
                        text = "Nenhum local favorito salvo.",
                        modifier = Modifier.padding(16.dp),
                        color = Color.Gray
                    )
                }
            }
        }
    }

@Composable
fun BodyActivity(){

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
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
        UpcomingCard(
            image = painterResource(id = R.drawable.reserveicon),
            title = "You have no upcoming \ntrips",
            subtitle = "Reserve your rides →"
        )
        Spacer(
            modifier = Modifier
                .height(1.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                "Past" , style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { },
                modifier = Modifier
                    .size(60.dp)
                    .wrapContentWidth(),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "",
                    modifier = Modifier
                        .size(10.dp),
                    Color.Black

                )
            }
        }
        Spacer(

            modifier = Modifier
                .height(1.dp)
        )
        ActivityCard(
            image = painterResource(id = R.drawable.mapa),
            title = "Universidade Positivo",
            subtitle = "Reserve your ridesSep 1 . 5:57 PM " +
                    "\nR$8,99"
        )
    }
}

@Composable
fun UpcomingCard(title: String, subtitle: String, image: Painter){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent), border = BorderStroke(1.dp, Color.Gray)
    ){
        Row (
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text(text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle ,
                    style = MaterialTheme.typography.bodySmall
                )
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
fun ActivityCard(image: Painter, title: String, subtitle: String){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent), border = BorderStroke(1.dp, Color.Gray)
    ){

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    painter = image,
                    contentDescription = title,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
                Text(text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle ,
                    style = MaterialTheme.typography.bodyLarge
                )
                Card (
                    modifier = Modifier
                        .width(120.dp)
                        .height(40.dp)
                ){
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(Color.LightGray),

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.rebook),
                            contentDescription = title,
                            modifier = Modifier
                                .weight(1f)
                        )
                        Spacer(
                            modifier = Modifier
                                .width(10.dp)
                        )
                        Text("Rebook", style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black)
                    }
                }
            }

    }
}
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