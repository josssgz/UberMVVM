package com.example.urber.ui.editprofile

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

@Composable
fun EditProfileScreen() {
    val context = LocalContext.current
    val viewModel: EditProfileViewModel = viewModel(
        factory = EditProfileViewModelFactory(
            UserRepository(AppDatabase.getDatabase(context).userDAO())
        )
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Usuários Cadastrados (${uiState.usuarios.size})",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.usuarios, key = { it.id }) { user ->
                UserCard(
                    user = user,
                    onEdit = { viewModel.onEditClick(it) },
                    onDelete = { viewModel.onDeleteUser(it) }
                )
            }
        }
    }

    if (uiState.usuarioEmEdicao != null) {
        UserEditDialog(
            uiState = uiState,
            viewModel = viewModel,
            onDismiss = { viewModel.onDismissDialog() },
            onSave = { viewModel.onSaveDialog() }
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
    uiState: EditProfileUiState,
    viewModel: EditProfileViewModel,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    val user = uiState.usuarioEmEdicao!!
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Usuário (ID: ${user.id})") },
        text = {
            Column {
                OutlinedTextField(
                    value = uiState.nomeEdit,
                    onValueChange = { viewModel.onEditNomeChange(it) },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.datanscEdit,
                    onValueChange = { viewModel.onEditDataNscChange(it) },
                    label = { Text("Data de Nascimento") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.sexoEdit,
                    onValueChange = { viewModel.onEditSexoChange(it) },
                    label = { Text("Sexo") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.enderecoEdit,
                    onValueChange = { viewModel.onEditEnderecoChange(it) },
                    label = { Text("Endereço") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.emailEdit,
                    onValueChange = { viewModel.onEditEmailChange(it) },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.passwordEdit,
                    onValueChange = { viewModel.onEditPasswordChange(it) },
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onSave
            ) { Text("Salvar") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}