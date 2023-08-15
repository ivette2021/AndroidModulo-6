package com.example.centrodeformacionfuturo.Modelo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.centrodeformacionfuturo.Modelo.local.CentroFuturoDao
import com.example.centrodeformacionfuturo.Modelo.local.Entities.CoursesDetailEntity
import com.example.centrodeformacionfuturo.Modelo.remote.RetrofitClient

class CentroFuturoRepository( private val centroFuturoDao: CentroFuturoDao) {

    // retrofit Cliente
    private val networkService = RetrofitClient.retrofitInstance()

    // dao listado
    val coursesListLiveData = centroFuturoDao.getAllCourses()

    // un elemento
    val courseDetailLiveData= MutableLiveData<CoursesDetailEntity>()


    suspend fun fetchCourse(){ //funcion asincrona , corrutina
        val service = kotlin.runCatching { networkService.fetchCourseList() } //Representa una instancia del servicio de red

        service.onSuccess {
            when (it.code()){
                in 200..299 ->it.body()?.let {// si el servicio responde con exito insertamos la lista de elementos

                    Log.d("Cursos",it.toString())


                    centroFuturoDao.insertAllCourses(fromInternetCoursesEntity(it))

                }
                else-> Log.d("Repo","${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}") //si falla captamos el error
            }
        }
    }

    suspend fun fetchCourseDetail(id: String): CoursesDetailEntity?{ //insertar cursos por id
        val service = kotlin.runCatching { networkService.fetchCourseDetail(id) }
        return service.getOrNull()?.body()?.let { courseDetail -> //retornamos el servicio, detalle de los cursos
            // guardp los datos que viene del mapper y luego se los paso a dao directo
            val courseDetailEntity = fromInternetCourseDetailEntity(courseDetail)
            //inserto los detalles de los curso DEL REPOSITORIO
            centroFuturoDao.insertCourseDetail(courseDetailEntity)
            courseDetailEntity
        }
    }

}