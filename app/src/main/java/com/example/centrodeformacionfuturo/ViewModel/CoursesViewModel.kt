package com.example.centrodeformacionfuturo.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.centrodeformacionfuturo.Modelo.CentroFuturoRepository
import com.example.centrodeformacionfuturo.Modelo.local.Entities.CoursesDetailEntity
import com.example.centrodeformacionfuturo.Modelo.local.Entities.CoursesEntity
import com.example.centrodeformacionfuturo.Modelo.local.database.CoursesDataBase
import kotlinx.coroutines.launch


class CoursesViewModel(application: Application) : AndroidViewModel(application){ //se agrega el contructor viewmodel de android

    // conexión con el repositorio
    private val repository : CentroFuturoRepository

    // referenciar las entidades primero referenciamos la lista de los cursos **
    private val courseDetailLiveData = MutableLiveData<CoursesDetailEntity>()

    // para seleccionar de una pantalla a otra
    private var idSelected : String="-1"

    init{
        // tiene la instancia de la base de datos, el dao, entregamos estas instancias al repositorio
        val bd= CoursesDataBase.getDataBase(application)
        val centrofuturoDao= bd.getCentroFuturoDao()

        repository = CentroFuturoRepository(centrofuturoDao)

        // llamo al método del repository

        viewModelScope.launch { //trabajamos en el hilo secundario
            repository.fetchCourse()
        }
    }

    // funcion listado de los elementos
    fun getCoursesList(): LiveData<List<CoursesEntity>> = repository.coursesListLiveData //referencia al repositorio

    // para obtener un elemento por id desde lo que se selecciono
    fun getCourseDetail(): LiveData<CoursesDetailEntity> = courseDetailLiveData

    // desde el segundo fragmento le paso la seleccion
    fun getCourseDetailByIdFromInternet(id: String)= viewModelScope.launch {//

        val courseDetail = repository.fetchCourseDetail(id)
        courseDetail?.let {

            courseDetailLiveData.postValue(it) //lo que seleccione se lo pasara al repositorio
        }
    }
}