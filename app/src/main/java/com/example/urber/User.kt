package com.example.urber

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val datansc: String,
    val sexo: String,
    val endereco: String,
    val email: String,
    val passwordHash: String
)