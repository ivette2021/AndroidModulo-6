package com.example.app_m6.Model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

//LA PRIMERA CLASE QUE VA A REPRESENTAR UNA TABLA

// AGREGAMOS LAS NOMENCLATURAS PARA DEFINIR UNA ENTIDAD SQLITE
@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    // la transformamos a dataclass y despues definimos sus atributos
    val idTask: Int =0,
    val task: String,
    val descripcion: String,
    val date: String

    )