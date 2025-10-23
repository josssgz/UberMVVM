package com.example.urber

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.urber.data.local.AppDatabase
import com.example.urber.data.local.User
import com.example.urber.data.repository.UserRepository
import com.example.urber.ui.editprofile.EditProfileUiState
import com.example.urber.ui.editprofile.EditProfileViewModel
import com.example.urber.ui.editprofile.EditProfileViewModelFactory

@Composable
fun EditProfileScreen() {
    // 1. Obter ViewModel e UiState
    val context = LocalContext.current
    val viewModel: EditProfileViewModel = viewModel(
        factory = EditProfileViewModelFactory(
            UserRepository(AppDatabase.getDatabase(context).userDAO())
        )
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // 2. Remover toda a lógica local (val db, val userDao, fun onDeleteUser, etc.)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Usuários Cadastrados (${uiState.usuarios.size})", // Ligar ao UiState
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.usuarios, key = { it.id }) { user -> // Ligar ao UiState
                UserCard(
                    user = user,
                    onEdit = { viewModel.onEditClick(it) }, // Chamar ViewModel
                    onDelete = { viewModel.onDeleteUser(it) } // Chamar ViewModel
                )
            }
        }
    }

    // 3. Controlar o diálogo pelo UiState
    if (uiState.usuarioEmEdicao != null) {
        UserEditDialog(
            uiState = uiState, // Passar o state
            viewModel = viewModel, // Passar o viewModel
            onDismiss = { viewModel.onDismissDialog() }, // Chamar ViewModel
            onSave = { viewModel.onSaveDialog() } // Chamar ViewModel
        )
    }
}

@Composable
fun UserCard(
    user: User,
    onEdit: (User) -> Unit,
    onDelete: (User) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Nome: ${user.nome}", fontWeight = FontWeight.Bold)
                Text("Email: ${user.email}")
                Text("Data de Nascimento: ${user.datansc}")
            }

            Row {
                IconButton(onClick = { onEdit(user) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = { onDelete(user) }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Deletar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun UserEditDialog(
    uiState: EditProfileUiState, // Recebe o UiState
    viewModel: EditProfileViewModel, // Recebe o ViewModel
    onDismiss: () -> Unit,
    onSave: () -> Unit // Não precisa mais do parâmetro User
) {
    // 4. Remover todos os 'var nomeEdit by remember { mutableStateOf(...) }'
    val user = uiState.usuarioEmEdicao!! // Sabemos que não é nulo

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Usuário (ID: ${user.id})") },
        text = {
            Column {
                OutlinedTextField(
                    value = uiState.nomeEdit, // Ligar ao UiState
                    onValueChange = { viewModel.onEditNomeChange(it) }, // Chamar ViewModel
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.datanscEdit, // Ligar ao UiState
                    onValueChange = { viewModel.onEditDataNscChange(it) }, // Chamar ViewModel
                    label = { Text("Data de Nascimento") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.sexoEdit, // Ligar ao UiState
                    onValueChange = { viewModel.onEditSexoChange(it) }, // Chamar ViewModel
                    label = { Text("Sexo") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.enderecoEdit, // Ligar ao UiState
                    onValueChange = { viewModel.onEditEnderecoChange(it) }, // Chamar ViewModel
                    label = { Text("Endereço") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.emailEdit, // Ligar ao UiState
                    onValueChange = { viewModel.onEditEmailChange(it) }, // Chamar ViewModel
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.passwordEdit, // Ligar ao UiState
                    onValueChange = { viewModel.onEditPasswordChange(it) }, // Chamar ViewModel
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onSave // Apenas chama o onSave
            ) { Text("Salvar") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}