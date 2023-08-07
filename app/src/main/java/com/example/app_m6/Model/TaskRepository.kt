package com.example.app_m6.Model

import androidx.lifecycle.LiveData
import com.example.app_m6.Model.model.Task
import com.example.app_m6.Model.model.TaskDao


//responsabilidad exponer los datos para el View-Model
//el respositorio expone las acciones del dao o modelo  a las capas superiores (vista, vista modelo) seria como el controlador mvc o es una copia del dao
class TaskRepository(private val taskDao: TaskDao) {

    //este value va a contener toda la info de la bD todas las tareas
    val listAllTask: LiveData<List<Task>> = taskDao.getAlltask1()

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteAlltask() {
        taskDao.deleteAlltask()
    }


}