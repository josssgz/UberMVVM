package com.example.urber.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable // ✅ adicione isso

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val datansc: String,
    val sexo: String,
    val endereco: String,
    val email: String,
    val password: String,
) : Serializable
