package com.example.urber

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerUser( user: User)

    @Query("SELECT * FROM userTable WHERE email = :email LIMIT 1")
    fun getUserByEmail( email: String): Flow<User?>
}