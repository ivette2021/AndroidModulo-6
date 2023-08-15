package com.example.centrodeformacionfuturo.Modelo.remote.frominternet

//representa la entidad que viene de internet
data class CourseDetail (
    val id : String,
    val title: String,
    val previewDescription: String,
    val image: String,
    val weeks: Int,
    val tuition : String,
    val minimumSkill : String,
    val scholarshipAvailable: Boolean,
    val modality : String,
    val start : String
)