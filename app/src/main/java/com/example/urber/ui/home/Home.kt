package com.example.urber

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.urber.data.local.AppDatabase
import com.example.urber.data.local.Places
import com.example.urber.data.repository.PlacesRepository
import com.example.urber.ui.home.HomeViewModel
import com.example.urber.ui.home.HomeViewModelFactory

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { HomeHeader() }
    ) { innerPadding ->
        HomeBody(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun HomeBody(navController: NavController, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { HomeWhereTo() } // ViewModel será injetado em HomeWhereTo
        item { HomeSuggestions() }
        item { HomeCardsWText() }
    }
}

@Composable
fun HomeWhereTo() {
    // 1. Obter ViewModel e UiState
    val context = LocalContext.current
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            PlacesRepository(AppDatabase.getDatabase(context).placesDAO())
        )
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // 2. Remover toda a lógica local (scope, placesDAO, places, placeToEdit)

    Column {
        Formulario(
            textoInput = uiState.whereToInput, // Ligar ao UiState
            onTextoChange = { viewModel.onWhereToInputChange(it) }, // Chamar ViewModel
            aoAdicionar = { viewModel.onAddPlace() } // Chamar ViewModel
        )
        Spacer(modifier = Modifier.height(10.dp))

        uiState.places.forEach { place -> // Ligar ao UiState
            PlaceItem(
                objPlace = place,
                onUpdate = { viewModel.onEditClick(it) }, // Chamar ViewModel
                onDelete = { viewModel.onDeletePlace(it) } // Chamar ViewModel
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }

    // 3. Controlar diálogo pelo UiState
    if(uiState.placeToEdit != null){
        EditPlaceDialog(
            editedText = uiState.editDialogText, // Ligar ao UiState
            onTextChange = { viewModel.onEditDialogTextChange(it) }, // Chamar ViewModel
            onDismiss = { viewModel.onDismissEditDialog() }, // Chamar ViewModel
            onSave = { viewModel.onSaveEditDialog() } // Chamar ViewModel
        )
    }
}

@Composable
fun EditPlaceDialog(
    editedText: String,
    onTextChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    // 4. Remover 'var editedText by remember'
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Local") },
        text = {
            TextField(
                value = editedText, // Ligar ao parâmetro
                onValueChange = onTextChange, // Chamar a função do parâmetro
                singleLine = true
            )
        },
        confirmButton = {
            Button(
                onClick = onSave, // Chamar a função do parâmetro
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green
                )
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun Formulario(
    textoInput: String,
    onTextoChange: (String) -> Unit,
    aoAdicionar: () -> Unit
) {
    // 5. Remover 'var textoInput by remember'
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textoInput, // Ligar ao parâmetro
            onValueChange = onTextoChange, // Chamar a função do parâmetro
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Row {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    Spacer(modifier = Modifier.width(2.dp))
                    Text("Where to?")
                }
            }
        )

        Spacer(modifier = Modifier.width(5.dp))

        Button(
            onClick = aoAdicionar, // Chamar a função do parâmetro
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Icone Add")
        }
    }
}

@Composable
fun PlaceItem(objPlace: Places, onUpdate: (Places) -> Unit, onDelete: (Places) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Place, contentDescription = null)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = objPlace.desc,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { onUpdate(objPlace) }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Place")
            }
            IconButton(onClick = { onDelete(objPlace) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Place")
            }
        }
    }
}

data class CardsWText(
    val title: String,
    val subtitle: String,
    val image: Int
)

// --- O restante do ficheiro (UI estática) permanece igual ---

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
            color = Color.Blue,
            text = "Send supplies across town",
            buttonText = "Get started",
            image = R.drawable.image
        ),
        CardContent(
            color = Color.Black,
            text = "Luxury on call",
            buttonText = "Try Uber Black",
            image = R.drawable.image
        ),
        CardContent(
            color = Color.Blue,
            text = "Ride on your schedule",
            buttonText = "Rerserve a ride",
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
                        .fillMaxWidth(),
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
                .fillMaxWidth()

        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }

}

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Uber",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold
        )
    }
}