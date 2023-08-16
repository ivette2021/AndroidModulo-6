package com.example.app_m6.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app_m6.Model.UsuarioRepository
import com.example.app_m6.Model.model.Usuario
import com.example.app_m6.Model.model.UsuarioDataBase
import kotlinx.coroutines.launch


//observar y recibir cambios de forma automatica
class UsuarioVM(application: Application) : AndroidViewModel(application) {

    // conexion con el repositorio
    private val repository: UsuarioRepository

    //livedata que expone la info del modelo (capa inferior)
    val allUsuario: LiveData<List<Usuario>>

    init {

        //inicializacion de las capas inferiores
        val UsuarioDao = UsuarioDataBase.getDatabase(application).getUsuarioDao() //instancia del dao que se comunicas con la bd
        repository = UsuarioRepository(UsuarioDao) //repositorio se comunica con el dao
        allUsuario = repository.listAllUsuario //llamamos toda la lista o LA TABLA
    }

    //ahora trabajamos con una corrutina para el ViewModel
    fun insertUsuario(usuario: Usuario) = viewModelScope.launch { //ejecutan en un hilo secundario
        repository.insertUsuario(usuario)
    }

    fun updateUsuario(usuario: Usuario) = viewModelScope.launch {
        repository.updateUsuario(usuario)
    }

    fun deleteUsuario(usuario: Usuario) = viewModelScope.launch {
        repository.deleteUsuario(usuario)
    }
  /*  fun getAllUsuario(usuario: Usuario) = viewModelScope.launch {
        repository.listAllUsuario(usuario)
    }*/

    fun retrieveAllUsuario(): LiveData<List<Usuario>> { // Cambio el nombre del método para evitar con
        return allUsuario
    }


    //para seleccionar algun elemento
    private val selectedUsuario: MutableLiveData<Usuario?> = MutableLiveData()


    //para seleccionar una tarea desde la vista por ejemplo  // para mostrar los elementos luego de una seleccion Recibir  el objeto seleccionado
    fun selectedItem(): LiveData<Usuario?> = selectedUsuario


    //funcion para recibir una tarea del recycler view
    fun selected(usuario: Usuario?) {
        //guarda lo que estamos seleccionando
        selectedUsuario.value = usuario
    }

}