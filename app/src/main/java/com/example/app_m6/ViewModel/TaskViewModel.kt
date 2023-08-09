package com.example.app_m6.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app_m6.Model.TaskRepository
import com.example.app_m6.Model.model.Task
import com.example.app_m6.Model.model.TaskDataBase
import kotlinx.coroutines.launch

//observar y recibir cambios de forma automatica
class TaskViewModel(application: Application) : AndroidViewModel(application) {


    // conexion con el repositorio
    private val repository: TaskRepository

    //livedata que expone la info del modelo (capa inferior)
    val allTask: LiveData<List<Task>>

    init {

        //inicializacion de las capas inferiores
        val TaskDao = TaskDataBase.getDatabase(application).getTaskDao() //instancia del dao que se comunicas con la bd
        repository = TaskRepository(TaskDao) //repositorio se comunica con el dao
        allTask = repository.listAllTask //llamamos toda la lista o LA TABLA
    }

    //ahora trabajamos con una corrutina para el ViewModel
    fun insertTask(task: Task) = viewModelScope.launch { //ejecutan en un hilo secundario
        repository.insertTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        repository.updateTask(task)
    }


    fun deleteTask() = viewModelScope.launch {
        repository.deleteAlltask()
    }

    //para seleccionar algun elemento
    private val selectedTask: MutableLiveData<Task?> = MutableLiveData()


    //para seleccionar una tarea desde la vista por ejemplo  // para mostrar los elementos luego de una seleccion Recibir  el objeto seleccionado
    fun selectedItem(): LiveData<Task?> = selectedTask


    //funcion para recibir una tarea del recycler view
    fun selected(task: Task?) {
        //guarda lo que estamos seleccionando
        selectedTask.value = task
    }

}