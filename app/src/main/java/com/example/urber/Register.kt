package com.example.urber

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavHostController) {
    Surface {
        Scaffold(containerColor = Color.Transparent) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Create your Urber Account",
                    style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Form(navController = navController, registerUser = { nome ->
                    // Aqui poderia salvar no banco de dados ou API
                })
            }
        }
    }
}

@Composable
fun Form(navController: NavHostController, registerUser: (String) -> Unit) {
    var nome by remember { mutableStateOf("") }
    var datansc by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Campo Nome
            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Data de Nascimento com Máscara
            DataNasc(
                datansc = datansc,
                onDateChange = { datansc = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Sexo
            TextField(
                value = sexo,
                onValueChange = { sexo = it },
                label = { Text("Sexo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Endereço
            TextField(
                value = endereco,
                onValueChange = { endereco = it },
                label = { Text("Endereço") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo E-mail
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Senha
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botão de Registro
            val context = LocalContext.current
            val db = AppDatabase.getDatabase(context)
            val userDao = db.userDAO()

            RegisterButton(onClickAction = {
                if (nome.isNotEmpty() && datansc.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

                    val novoUser = User(
                        nome = nome,
                        datansc = datansc,
                        sexo = sexo,
                        endereco = endereco,
                        email = email,
                        password = password
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        userDao.registerUser(novoUser)
                    }

                    Toast.makeText(context, "Registro realizado com sucesso!", Toast.LENGTH_LONG).show()
                    navController.navigate("login")
                } else {
                    Toast.makeText(context, "Preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show()
                }
            })


            Spacer(modifier = Modifier.height(16.dp))

            // Link para Login
            Text(
                "Log in",
                color = Color.Black,
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun RegisterButton(onClickAction: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
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
        val trimmedText =
            if (text.text.length >= dateLength) text.text.substring(0, dateLength) else text.text

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
                val nonDigitCount = specialSymbolsIndices.count { it < offset }
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
