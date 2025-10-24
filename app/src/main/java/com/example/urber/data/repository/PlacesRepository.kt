package com.example.urber.data.repository

import com.example.urber.data.local.Places
import com.example.urber.data.local.PlacesDAO
import kotlinx.coroutines.flow.Flow

class PlacesRepository(private val placesDAO: PlacesDAO) {

    fun getAllPlaces(): Flow<List<Places>> {
        return placesDAO.getAllPlaces()
    }

    suspend fun addPlace(place: Places) {
        placesDAO.addPlace(place)
    }

    suspend fun updatePlace(place: Places) {
        placesDAO.updatePlace(place)
    }

    suspend fun deletePlace(place: Places) {
        placesDAO.deletePlace(place)
    }
}