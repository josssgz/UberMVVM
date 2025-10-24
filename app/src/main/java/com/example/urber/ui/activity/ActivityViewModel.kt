package com.example.urber.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.urber.data.local.Places
import com.example.urber.data.repository.PlacesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ActivityUiState(
    val savedPlaces: List<Places> = emptyList()
)

class ActivityViewModel(private val repository: PlacesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ActivityUiState())
    val uiState: StateFlow<ActivityUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllPlaces().collect { placeList ->
                _uiState.update { it.copy(savedPlaces = placeList) }
            }
        }
    }
}

class ActivityViewModelFactory(private val repository: PlacesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}