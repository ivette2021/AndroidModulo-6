package com.example.app_m6.Model.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.app_m6.Model.model.Task

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
    suspend fun insertMultipleTask(list: List<Task>)

    //actualizar
    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteOneTask(task: Task)

    //creamos una funcion para borrar todo db
    @Query("DELETE FROM TASK_TABLE")
    suspend fun  deleteAlltask()

    @Query("SELECT * FROM TASK_TABLE")
    // fun getAlltask1(): List<Task>
    fun getAlltask1(): LiveData <List<Task>> //usando livedata, esto ya seria un return

    //ejemplo para traer todas las tareas ordenadas por id
    @Query("SELECT * FROM TASK_TABLE ORDER BY idTask ASC")
    //fun getAlltask(): List<Task>
     fun getAlltask(): LiveData<List<Task>>

    //ejemplo de traer solo una tarea
    @Query("SELECT * FROM TASK_TABLE WHERE id=:id")
    //fun getTaskById(idTask: Int): Task
    fun getTaskByTitle(title: String): LiveData<Task>

    //ejemplo de traer por titulo usando LiveData
    @Query("SELECT * FROM TASK_TABLE WHERE title=:title Limit 1")
    fun TaskByRTitle(title: String) : LiveData<Task>
}