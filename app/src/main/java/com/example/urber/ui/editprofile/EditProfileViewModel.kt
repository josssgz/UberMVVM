package com.example.urber.ui.editprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.urber.data.local.User
import com.example.urber.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EditProfileUiState(
    val usuarios: List<User> = emptyList(),
    val usuarioEmEdicao: User? = null,
    val nomeEdit: String = "",
    val datanscEdit: String = "",
    val sexoEdit: String = "",
    val enderecoEdit: String = "",
    val emailEdit: String = "",
    val passwordEdit: String = ""
)

class EditProfileViewModel(private val repository: UserRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState: StateFlow<EditProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllUsers().collect { userList ->
                _uiState.update { it.copy(usuarios = userList) }
            }
        }
    }

    fun onDeleteUser(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }

    fun onEditClick(user: User) {
        _uiState.update {
            it.copy(
                usuarioEmEdicao = user,
                nomeEdit = user.nome,
                datanscEdit = user.datansc,
                sexoEdit = user.sexo,
                enderecoEdit = user.endereco,
                emailEdit = user.email,
                passwordEdit = user.password
            )
        }
    }

    fun onDismissDialog() {
        _uiState.update {
            it.copy(
                usuarioEmEdicao = null,
                nomeEdit = "", datanscEdit = "", sexoEdit = "",
                enderecoEdit = "", emailEdit = "", passwordEdit = ""
            )
        }
    }

    fun onSaveDialog() {
        val state = _uiState.value
        val userAtualizado = state.usuarioEmEdicao?.copy(
            nome = state.nomeEdit,
            datansc = state.datanscEdit,
            sexo = state.sexoEdit,
            endereco = state.enderecoEdit,
            email = state.emailEdit,
            password = state.passwordEdit
        )

        if (userAtualizado != null) {
            viewModelScope.launch {
                repository.updateUser(userAtualizado)
                onDismissDialog()
            }
        }
    }

    fun onEditNomeChange(nome: String) { _uiState.update { it.copy(nomeEdit = nome) } }
    fun onEditDataNscChange(data: String) { _uiState.update { it.copy(datanscEdit = data) } }
    fun onEditSexoChange(sexo: String) { _uiState.update { it.copy(sexoEdit = sexo) } }
    fun onEditEnderecoChange(end: String) { _uiState.update { it.copy(enderecoEdit = end) } }
    fun onEditEmailChange(email: String) { _uiState.update { it.copy(emailEdit = email) } }
    fun onEditPasswordChange(pass: String) { _uiState.update { it.copy(passwordEdit = pass) } }
}

class EditProfileViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}