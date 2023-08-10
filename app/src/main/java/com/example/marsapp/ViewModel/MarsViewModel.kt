package com.example.marsapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.marsapp.Model.Local.MarsDataBase
import com.example.marsapp.Model.Remote.MarsRealState
import com.example.marsapp.Model.MarsRepository
import kotlinx.coroutines.launch

class MarsViewModel (application: Application): AndroidViewModel(application) { //implementar el repo y todas las tareas


    // parte 1 comunicar con el repository

    private val repository: MarsRepository //variable que representa al repositorio

    // parte 2
    lateinit var liveDatafromInternet: LiveData<List<MarsRealState>>//lo que esta en internet

    // para mostrar todas las tareas   parte 2

    val allTerrains: LiveData<List<MarsRealState>>//lo que esta guardado en la base de datos nuestra

    init { //inicializamos las variables anteriores
        //parte 1 funciona sin el dao
        // repository= MarsRepository()
        // liveDatafromInternet = repository.fetchDataMars() //llamas a la funcion sin corrutina

        // parte 2 estoy llamando la instancia del Dao en la base de datos
        val MarsDao = MarsDataBase.getDataBase(application).getMarsDao()
        repository = MarsRepository(MarsDao) //instancia del repositorio

        // parte 2 con corrutinas y viewmodel

        viewModelScope.launch {

            repository.fetchDataFromInternetCoroutines()
        }
        // cierre parte 2

        allTerrains = repository.listAllTerrain //

        /**********parte 1***********************/
        liveDatafromInternet = repository.dataFromInternet //

    }


    //******************************Estos métodos son con el dao****************/

    private var selectedMarsTerrains: MutableLiveData<MarsRealState> = MutableLiveData()  // guardar la seleccion en una mutableLiveDATA

    fun selected(mars: MarsRealState) { //funcion para seleccionar un terreno
        selectedMarsTerrains.value = mars
    }

    fun selectedItem(): LiveData<MarsRealState> = selectedMarsTerrains


    fun insertTerrain(mars: MarsRealState) = viewModelScope.launch { //trabajo con corrutinas las funciones suspendidas
        repository.insertTerrain(mars)
    }

    fun updateTerrain(mars: MarsRealState) = viewModelScope.launch {// se ejecuta en el hilo principal
        repository.updateTerrain(mars)
    }

    fun deleteTerrain(mars: MarsRealState) = viewModelScope.launch {// se ejecuta en el hilo principal
        repository.deleteTerrain(mars)
    }

    fun deleteAllTerrain(mars: MarsRealState) = viewModelScope.launch {// se ejecuta en el hilo principal
        repository.deleteAll(mars)
    }
    /*fun getMarsById(id:Int): LiveData<MarsRealState>{
        return  repository.getMarsById(id)
    }*/
}