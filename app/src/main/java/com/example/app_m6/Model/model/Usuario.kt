package com.example.app_m6.Model.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

//LA PRIMERA CLASE QUE VA A REPRESENTAR UNA TABLA

// AGREGAMOS LAS NOMENCLATURAS PARA DEFINIR UNA ENTIDAD SQLITE
@Entity(tableName = "usuario_table")
// la transformamos a dataclass y despues definimos sus atributos
data class Usuario (
    @PrimaryKey(autoGenerate = true)
    @NonNull
// sqlite mapea el objeto y lo transfroma en tabla
    val id: Int =0,
    val usuario: String,
    val nombre: String,
    val edad: Int

)