package com.example.apidog.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apidog.Model.DogRepository
import com.example.apidog.Model.Local.DataBase.DogDataBase
import com.example.apidog.Model.Local.Entities.DogDetailEntity
import com.example.apidog.Model.Local.Entities.DogEntity
import kotlinx.coroutines.launch

class DogViewModel(application: Application) : AndroidViewModel(application)  { //se agrega el contructor viewmodel de android

    // conexión con el repositorio
    private val repository : DogRepository

    // referenciar las entidades primero referenciamos la lista de perros
    private val dogDetailLiveData = MutableLiveData<DogDetailEntity>()

    // para seleccionar de una pantalla a otra                                                 ****
    private var messageSelected : String="-1"

    init{
        // tiene la instancia de la base de datos, el dao, entregamos estas instancias al repositorio
        val bd= DogDataBase.getDataBase(application)
        val dogDao= bd.getDogDao()

        repository = DogRepository(dogDao)

        // llamo al método del repository

        viewModelScope.launch { //trabajamos en el hilo secundario
            repository.fetchDog()
        }
    }

    // funcion listado de los elementos
    fun selectedDog1(): LiveData<List<DogEntity>> = selectedDog1()

    fun getDogList(): LiveData<List<DogEntity>> = repository.dogListLiveData //referencia al repositorio

    // para obtener un elemento por id desde lo que se selecciono
    fun getDogDetail(): LiveData<DogDetailEntity> = dogDetailLiveData

    // desde el segundo fragmento le paso la seleccion
    fun getDogDetailBymessageFromInternet(message: String)= viewModelScope.launch {//

        val dogDetail = repository.fetchDogDetail(message)
        dogDetail?.let {

           dogDetailLiveData.postValue(it) //lo que seleccione se lo pasara al repositorio
        }
    }
}