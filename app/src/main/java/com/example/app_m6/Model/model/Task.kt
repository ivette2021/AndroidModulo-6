package com.example.app_m6.Model.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

//LA PRIMERA CLASE QUE VA A REPRESENTAR UNA TABLA

// AGREGAMOS LAS NOMENCLATURAS PARA DEFINIR UNA ENTIDAD SQLITE
@Entity(tableName = "task_table")
// la transformamos a dataclass y despues definimos sus atributos
data class Task (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    // sqlite mapea el objeto y lo transfroma en tabla
    val task: String,
    //val idTask: Int =0,
    val descripcion: String,
    val date: String,

//    val title: String,
//    val priority: Int,
//    val state: Boolean


    )
