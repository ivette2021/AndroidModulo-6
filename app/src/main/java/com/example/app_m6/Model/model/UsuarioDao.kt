package com.example.app_m6.Model.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


//INTERFAZ REPRESENTA LAS ACCIONES QUE VAMOS HACER EN NUESTRA BASE DE DATOS
@Dao
interface UsuarioDao {  // usamos el @ para realizar una accion ej: @Insert(onConflict = OnConflictStrategy.REPLACE)
    //ojo que todos se estan trabajando en el mismo hilo no estamos usando corrutinas aqui.
    //cada dao para cada tabla principio de responsabilidad unica
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertUsuario(usuario: Usuario)

    //insertar multiples tareas
    @Insert
    suspend fun insertMultipleUsuario(list: List<Usuario>)

    //actualizar
    @Update
    suspend fun updateUsuario(usuario: Usuario)

    @Delete
    suspend fun deleteOneUsuario(usuario: Usuario)

    //creamos una funcion para borrar todo db
    @Query("DELETE FROM USUARIO_TABLE")
    suspend fun  deleteAllUsuario()

    @Query("SELECT * FROM USUARIO_TABLE")
    // fun getAllUsuario1(): List<Usuario>
    fun getAllUsuario1(): LiveData<List<Usuario>> //usando livedata, esto ya seria un return

    //ejemplo para traer todas las tareas ordenadas por id
    @Query("SELECT * FROM USUARIO_TABLE ORDER BY id ASC")
    //fun getAllUsuario(): List<Usuario>
    fun getAllUsuario(): LiveData<List<Usuario>>


    //ejemplo de traer por titulo usando LiveData
    @Query("SELECT * FROM USUARIO_TABLE WHERE usuario=:usuario Limit 1")
    fun UsuarioByRUsuario(usuario: String) : LiveData<Usuario>


    // Traer por id
    @Query("SELECT * FROM USUARIO_TABLE WHERE  id=:id")
    fun getUsuarioById(id:Int): LiveData<Usuario>


}