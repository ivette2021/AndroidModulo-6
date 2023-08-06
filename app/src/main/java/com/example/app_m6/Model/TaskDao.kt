package com.example.app_m6.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

//INTERFAZ REPRESENTA LAS ACCIONES QUE VAMOS HACER EN NUESTRA BASE DE DATOS
@Dao
interface TaskDao {
    // usamos el @ para realizar una accion ej: @Insert(onConflict = OnConflictStrategy.REPLACE)
    //ojo que todos se estan trabajando en el mismo hilo no estamos usando corrutinas aqui.
    //cada dao para cada tabla principio de responsabilidad unica
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    //insertar multiples tareas
    @Insert
    fun insertMultipleTask(list: List<Task>)

    //actualizar
    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteOneTask(task: Task)

    //creamos una funcion para borrar todo db
    @Query("DELETE FROM TASK_TABLE")
    suspend fun  deleteAlltask()

    @Query("SELECT * FROM TASK_TABLE")
    fun getAlltask1(): List<Task>

    //ejemplo para traer todas las tareas ordenadas por id
    @Query("SELECT * FROM TASK_TABLE ORDER BY idTask ASC")
    fun getAlltask(): List<Task>

    //ejemplo de traer solo una tarea
//    @Query("SELECT * FROM TASK_TABLE WHERE idTask = idTask Limit 1")
//    fun getTaskById(idTask: Int): Task
}