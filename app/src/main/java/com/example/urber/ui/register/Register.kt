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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.urber.data.local.AppDatabase
import com.example.urber.data.repository.UserRepository
import com.example.urber.ui.register.RegisterUiState
import com.example.urber.ui.register.RegisterViewModel
import com.example.urber.ui.register.RegisterViewModelFactory

@Composable
fun RegisterScreen(navController: NavHostController) {
    // 1. Obter o ViewModel e o UiState
    val context = LocalContext.current
    val viewModel: RegisterViewModel = viewModel(
        factory = RegisterViewModelFactory(
            UserRepository(AppDatabase.getDatabase(context).userDAO())
        )
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // 2. Efeito para lidar com a navegação pós-registo
    LaunchedEffect(key1 = uiState.registrationSuccess) {
        if (uiState.registrationSuccess) {
            Toast.makeText(context, "Registro realizado com sucesso!", Toast.LENGTH_LONG).show()
            navController.navigate("login") {
                // Limpa a backstack para que o utilizador não possa voltar ao registo
                popUpTo("register") { inclusive = true }
            }
            viewModel.onRegistrationHandled() // Avisa o ViewModel que a navegação foi tratada
        }
    }

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

                // 3. Passar o estado e o viewModel para o formulário
                Form(
                    navController = navController,
                    uiState = uiState,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun Form(
    navController: NavHostController,
    uiState: RegisterUiState, // Recebe o UiState
    viewModel: RegisterViewModel // Recebe o ViewModel
) {
    // 4. Remover todos os 'var nome by remember { mutableStateOf("") }'

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
                value = uiState.nome, // Ligar ao UiState
                onValueChange = { viewModel.onNomeChange(it) }, // Chamar o ViewModel
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Data de Nascimento com Máscara
            DataNasc(
                datansc = uiState.datansc, // Ligar ao UiState
                onDateChange = { viewModel.onDataNscChange(it) } // Chamar o ViewModel
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Sexo
            TextField(
                value = uiState.sexo, // Ligar ao UiState
                onValueChange = { viewModel.onSexoChange(it) }, // Chamar o ViewModel
                label = { Text("Sexo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Endereço
            TextField(
                value = uiState.endereco, // Ligar ao UiState
                onValueChange = { viewModel.onEnderecoChange(it) }, // Chamar o ViewModel
                label = { Text("Endereço") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo E-mail
            TextField(
                value = uiState.email, // Ligar ao UiState
                onValueChange = { viewModel.onEmailChange(it) }, // Chamar o ViewModel
                label = { Text("E-mail") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Senha
            TextField(
                value = uiState.password, // Ligar ao UiState
                onValueChange = { viewModel.onPasswordChange(it) }, // Chamar o ViewModel
                label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botão de Registro
            // 5. Remover toda a lógica de DB e Coroutine daqui
            RegisterButton(onClickAction = {
                // 6. Apenas notificar o ViewModel
                viewModel.onRegisterClick()
                // A lógica de Toast/Navegação está no LaunchedEffect
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
    // A lógica de validação (maxDigits) foi para o ViewModel
    TextField(
        value = datansc,
        onValueChange = { novoValor ->
            onDateChange(novoValor) // Apenas passa o valor para o ViewModel
        },
        label = { Text("Data de Nascimento (DD/MM/AAAA)") },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = DateMaskTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}