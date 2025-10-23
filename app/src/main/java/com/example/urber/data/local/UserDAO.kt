package com.example.urber

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerUser( user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>
    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}