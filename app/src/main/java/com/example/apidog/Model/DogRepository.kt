package com.example.apidog.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.apidog.Model.Local.DogDao
import com.example.apidog.Model.Local.Entities.DogDetailEntity
import com.example.apidog.Model.Remote.RetrofitClient

class DogRepository(private val dogDao: DogDao) {

    // retrofit Cliente
    private val networkService = RetrofitClient.retrofitInstance()

    // dao listado
    val dogListLiveData = dogDao.getAllDogs()

    // un elemento
    val dogDetailLiveData= MutableLiveData<DogDetailEntity>()


    suspend fun fetchDog(){ //funcion asincrona , corrutina
        val service = kotlin.runCatching { networkService.fetchDogList() } //Representa una instancia del servicio de red

        service.onSuccess {
            when (it.code()){
                in 200..299 ->it.body()?.let {// si el servicio responde con exito insertamos la lista de plantas

                    Log.d("Perros",it.toString())

                    dogDao.insertAllDogs(fromInternetDogEntity(it))

                }
                else-> Log.d("Repo","${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}") //si falla captamos el error
            }
        }
    }

    suspend fun fetchDogDetail(message: String): DogDetailEntity?{ //insertar plantas por id
        val service = kotlin.runCatching { networkService.fetchDogDetail(message) }
        return service.getOrNull()?.body()?.let { dogDetail -> //retornamos el servicio, detalle de las plantas
            // guardp los datos que viene del mapper y luego se los paso a dao directo
            val dogDetailEntity = fromInternetDogDetailEntity(dogDetail)
            //inserto los detalles de cada planta  del repositorio
            dogDao.insertDogDetail(dogDetailEntity)
            dogDetailEntity
        }
    }

}