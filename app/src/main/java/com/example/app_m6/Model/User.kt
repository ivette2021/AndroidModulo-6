package com.example.app_m6.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val gameName: String,
    val fullName: String,
    val age: Int
)
