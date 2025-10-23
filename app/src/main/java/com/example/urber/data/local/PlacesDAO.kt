package com.example.urber

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PlacesDAO{

    @Insert
    suspend fun addPlace(place: Places)

    @Query("SELECT * FROM places ORDER BY id ASC")
    fun getAllPlaces() : Flow<List<Places>>

    @Update
    suspend fun updatePlace(places: Places)

    @Delete
    suspend fun deletePlace(places: Places)

}