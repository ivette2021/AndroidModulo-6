package com.example.apidog.Model.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Objects

@Entity(tableName = "dog_list_table")
data class DogEntity(
//colocamos los parametros de la api courses
    @PrimaryKey
    val message: String,
    val status: String
    )