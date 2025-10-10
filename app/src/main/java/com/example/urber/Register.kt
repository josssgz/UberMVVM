package com.example.urber

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


class UserViewModel : ViewModel() {
    fun registerUser(nome: String, dataNasc: String, sexo: String, endereco: String, email: String, password: String) {
        println("Registrando via ViewModel: Nome=$nome, Data=$dataNasc, Email=$email")
    }
}
// ----------------------------------------------------------------------

@Composable
fun RegisterScreen(navController: NavHostController){
    Surface {
        Scaffold(containerColor = Color.Transparent) {
                innerPadding ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Spacer(modifier = Modifier.height(32.dp))

                Text(text = "Create your Urber Account", style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp))

                Spacer(modifier = Modifier.height(16.dp))

                Form(navController = navController)

            }
        }
    }
}


@Composable

fun Form(navController: NavHostController){

    val userViewModel: UserViewModel = viewModel()
    var nome by remember { mutableStateOf("") }
    var datansc by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface (
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Transparent

    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            TextField(
                value = nome,
                onValueChange = { nome = it},
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            DataNasc(
                datansc = datansc,
                onDateChange = { datansc = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = sexo,
                onValueChange = { sexo = it},
                label = { Text("Sexo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = endereco,
                onValueChange = { endereco = it},
                label = { Text("Endereço") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it},
                label = { Text("E-mail") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it},
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            RegisterButton(onClickAction = {
                userViewModel.registerUser(
                    nome = nome,
                    dataNasc = datansc,
                    sexo = sexo,
                    endereco = endereco,
                    email = email,
                    password = password
                )
            })

            Spacer(modifier = Modifier.height(16.dp))

            Text("Log in",
                modifier = Modifier.clickable {
                    navController.navigate("login")
                })

            Spacer(modifier = Modifier.height(32.dp)) // Espaçamento inferior para o scroll
        }
    }
}

@Composable
fun RegisterButton(onClickAction: () -> Unit){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = onClickAction,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )

            ) {
                Text("Register")
            }
        }
    }
}
class DateMaskTransformation(private val mask: String = "##/##/####") : VisualTransformation {
    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }
    private val dateLength = mask.count { it == '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmedText = if (text.text.length >= dateLength) text.text.substring(0, dateLength) else text.text

        var out = ""
        var maskIndex = 0
        trimmedText.forEach { char ->
            while (maskIndex < mask.length && mask[maskIndex] != '#') {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }


        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset == 0) return 0
                val nonDigitCount = specialSymbolsIndices.count { it < offset + specialSymbolsIndices.count { it < offset } }
                return offset + nonDigitCount
            }

            override fun transformedToOriginal(offset: Int): Int {
                val nonDigitCount = specialSymbolsIndices.count { it < offset }
                return offset - nonDigitCount
            }
        }

        return TransformedText(AnnotatedString(out), offsetTranslator)
    }
}

@Composable
fun DataNasc(datansc: String, onDateChange: (String) -> Unit) {
    val maxDigits = 8

    TextField(
        value = datansc,
        onValueChange = { novoValor ->
            val cleanValue = novoValor.filter { it.isDigit() }
            if (cleanValue.length <= maxDigits) {
                onDateChange(cleanValue)
            }
        },
        label = { Text("Data de Nascimento (DD/MM/AAAA)") },
        modifier = Modifier.fillMaxWidth(),

        visualTransformation = DateMaskTransformation(),

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}