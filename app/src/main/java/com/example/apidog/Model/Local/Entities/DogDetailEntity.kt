package com.example.apidog.Model.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Objects

@Entity(tableName="dog_detail_table")
class DogDetailEntity(
//datos cuando consultamos un elemento de la lista de la api
    @PrimaryKey
    val message: String,
    val status: String

)