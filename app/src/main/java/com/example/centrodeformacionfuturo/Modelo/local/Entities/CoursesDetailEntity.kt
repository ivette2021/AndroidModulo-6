package com.example.centrodeformacionfuturo.Modelo.local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="course_detail_table")
class CoursesDetailEntity(
//datos cuando consultamos un elemento de la lista de la api
    @PrimaryKey
    val id : String,
    val title: String,
    val previewDescription: String,
    val image: String,
    val weeks: Int,
    val tuition : String,
    val minimumSkill : String,
    val scholarshipAvailable: Boolean,
    val modality : String,
    val star : String


)