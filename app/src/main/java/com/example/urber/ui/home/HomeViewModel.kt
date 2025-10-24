package com.example.urber.ui.home

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

data class HomeUiState(
    val places: List<Places> = emptyList(),
    val whereToInput: String = "",
    val placeToEdit: Places? = null,
    val editDialogText: String = ""
)

class HomeViewModel(private val repository: PlacesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllPlaces().collect { placeList ->
                _uiState.update { it.copy(places = placeList) }
            }
        }
    }

    fun onWhereToInputChange(newText: String) {
        _uiState.update { it.copy(whereToInput = newText) }
    }

    fun onAddPlace() {
        val text = _uiState.value.whereToInput
        if (text.isNotBlank()) {
            viewModelScope.launch {
                repository.addPlace(Places(desc = text))
                _uiState.update { it.copy(whereToInput = "") }
            }
        }
    }

    fun onDeletePlace(place: Places) {
        viewModelScope.launch {
            repository.deletePlace(place)
        }
    }

    fun onEditClick(place: Places) {
        _uiState.update { it.copy(placeToEdit = place, editDialogText = place.desc) }
    }

    fun onEditDialogTextChange(newText: String) {
        _uiState.update { it.copy(editDialogText = newText) }
    }

    fun onDismissEditDialog() {
        _uiState.update { it.copy(placeToEdit = null, editDialogText = "") }
    }

    fun onSaveEditDialog() {
        val state = _uiState.value
        val updatedPlace = state.placeToEdit?.copy(desc = state.editDialogText)
        if (updatedPlace != null && state.editDialogText.isNotBlank()) {
            viewModelScope.launch {
                repository.updatePlace(updatedPlace)
                onDismissEditDialog()
            }
        }
    }
}

class HomeViewModelFactory(private val repository: PlacesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}