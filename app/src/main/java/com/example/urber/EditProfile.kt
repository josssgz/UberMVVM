package com.example.urber

import android.util.Log
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen() {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val userDao = db.userDAO()

    val usuarios by userDao.getAllUsers().collectAsState(initial = emptyList())

    var showEditDialog by remember { mutableStateOf(false) }
    var usuarioSelecionado by remember { mutableStateOf<User?>(null) }

    fun onDeleteUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userDao.deleteUser(user)
            } catch (e: Exception) {
                Log.e("UserDAO", "Erro ao deletar: ${e.message}")
            }
        }
    }

    fun onUpdateUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userDao.updateUser(user)
            } catch (e: Exception) {
                Log.e("UserDAO", "Erro ao atualizar: ${e.message}")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Usuários Cadastrados (${usuarios.size})",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(usuarios, key = { it.id }) { user ->
                UserCard(
                    user = user,
                    onEdit = {
                        usuarioSelecionado = it
                        showEditDialog = true
                    },
                    onDelete = { onDeleteUser(it) }
                )
            }
        }
    }

    if (showEditDialog && usuarioSelecionado != null) {
        UserEditDialog(
            user = usuarioSelecionado!!,
            onDismiss = {
                showEditDialog = false
                usuarioSelecionado = null
            },
            onSave = { atualizado ->
                onUpdateUser(atualizado)
                showEditDialog = false
                usuarioSelecionado = null
            }
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
    user: User,
    onDismiss: () -> Unit,
    onSave: (User) -> Unit
) {
    var nomeEdit by remember { mutableStateOf(user.nome) }
    var datanscEdit by remember { mutableStateOf(user.datansc) }
    var sexoEdit by remember { mutableStateOf(user.sexo) }
    var enderecoEdit by remember { mutableStateOf(user.endereco) }
    var emailEdit by remember { mutableStateOf(user.email) }
    var passwordEdit by remember { mutableStateOf(user.password) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Usuário (ID: ${user.id})") },
        text = {
            Column {
                OutlinedTextField(
                    value = nomeEdit,
                    onValueChange = { nomeEdit = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = datanscEdit,
                    onValueChange = { datanscEdit = it },
                    label = { Text("Data de Nascimento") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = sexoEdit,
                    onValueChange = { sexoEdit = it },
                    label = { Text("Sexo") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = enderecoEdit,
                    onValueChange = { enderecoEdit = it },
                    label = { Text("Endereço") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = emailEdit,
                    onValueChange = { emailEdit = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = passwordEdit,
                    onValueChange = { passwordEdit = it },
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSave(
                        user.copy(
                            nome = nomeEdit,
                            datansc = datanscEdit,
                            sexo = sexoEdit,
                            endereco = enderecoEdit,
                            email = emailEdit,
                            password = passwordEdit
                        )
                    )
                }
            ) { Text("Salvar") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
