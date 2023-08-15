package com.example.centrodeformacionfuturo.Modelo.local.Entities


import androidx.room.Entity
import androidx.room.PrimaryKey

//convertimos en una dataclass
@Entity(tableName="courses_list_table")
data class CoursesEntity (
//colocamos los parametros de la api courses
    @PrimaryKey
    val id : String,
    val title: String,
    val previewDescription: String,
    val image: String,
    val weeks: Int,
    val star : String



)