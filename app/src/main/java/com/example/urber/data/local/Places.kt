package com.example.urber.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class Places(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val desc: String
)