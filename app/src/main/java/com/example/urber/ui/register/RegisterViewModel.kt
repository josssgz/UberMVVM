package com.example.urber.ui.register

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

data class RegisterUiState(
    val nome: String = "",
    val datansc: String = "",
    val sexo: String = "",
    val endereco: String = "",
    val email: String = "",
    val password: String = "",
    val registrationSuccess: Boolean = false
)

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNomeChange(novoNome: String) {
        _uiState.update { it.copy(nome = novoNome) }
    }
    fun onDataNscChange(novaData: String) {
        val cleanValue = novaData.filter { it.isDigit() }
        if (cleanValue.length <= 8) {
            _uiState.update { it.copy(datansc = cleanValue) }
        }
    }
    fun onSexoChange(novoSexo: String) {
        _uiState.update { it.copy(sexo = novoSexo) }
    }
    fun onEnderecoChange(novoEndereco: String) {
        _uiState.update { it.copy(endereco = novoEndereco) }
    }
    fun onEmailChange(novoEmail: String) {
        _uiState.update { it.copy(email = novoEmail) }
    }
    fun onPasswordChange(novaPassword: String) {
        _uiState.update { it.copy(password = novaPassword) }
    }

    fun onRegisterClick() {
        val state = _uiState.value
        if (state.nome.isEmpty() || state.datansc.isEmpty() || state.email.isEmpty() || state.password.isEmpty()) {
            return
        }

        val novoUser = User(
            nome = state.nome,
            datansc = state.datansc,
            sexo = state.sexo,
            endereco = state.endereco,
            email = state.email,
            password = state.password
        )

        viewModelScope.launch {
            repository.registerUser(novoUser)
            _uiState.update { it.copy(registrationSuccess = true) }
        }
    }

    fun onRegistrationHandled() {
        _uiState.update { it.copy(registrationSuccess = false) }
    }
}

class RegisterViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}