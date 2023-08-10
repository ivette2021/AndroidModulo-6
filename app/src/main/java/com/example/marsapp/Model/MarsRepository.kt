package com.example.marsapp.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marsapp.Model.Local.MarsDao
import com.example.marsapp.Model.Remote.MarsRealState
import com.example.marsapp.Model.Remote.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class MarsRepository(private val marsDao: MarsDao) { //repositorio trabaja con el dao , le generamos una representacion, instancia del contructor


    // 1 llamar al método de conexion o instancia de retrofit
//variable tipo conexion ocupa la funcion de retrofit para conectarse
    private val retrofitClient = RetrofitClient.getRetrofit()


    //2 hace referencia a POJO Y la respuesta que vamos a recibir
//representa el listado donde van a provenir de alguna forma
    val dataFromInternet = MutableLiveData<List<MarsRealState>>()


    // sin corrutinas
    fun fetchDataMars(): LiveData<List<MarsRealState>> { //recibira un listado livedata
        Log.d("Repositorio", "Sin Corrutina")
        retrofitClient.fetchMarsData().enqueue(object : retrofit2.Callback<List<MarsRealState>> { //interactua retrofit con api llamadop de los terrenos de martes
            override fun onResponse(
                call: Call<List<MarsRealState>>,//respuesta tiene que ver con retrofit
                response: Response<List<MarsRealState>>//respuesta es un listado
            ) {

                when (response.code()) { //cuando la respuesta es :

                    in 200..299 -> dataFromInternet.value = response.body()
                    in 300..301 -> Log.d("Repositorio", "${response.code()} ---${response.errorBody()}")
                    else -> Log.d("E", "${response.code()} ---${response.errorBody()}")
                }

            }

            override fun onFailure(call: Call<List<MarsRealState>>, t: Throwable) { //llamado a la lista
                Log.e("Error", " ${t.message}")
            }

        })

        return dataFromInternet //retornamos la lista de los elementos


    }

    // Forma con coroutinas

    suspend fun fetchDataFromInternetCoroutines(){
        try { //usamos el try catch
            val response = retrofitClient.fetchMarsDataCoroutines()//
            when (response.code()) {
                //  in 200..299 -> dataFromInternet.value = response.body()
                in 200..299 -> response?.body().let{
                    if (it != null) {
                        marsDao.insertAllTerrain(it)
                    }

                }
                in 300..301 -> Log.d("REPO", "${response.code()} --- ${response.errorBody()}")
                else -> Log.d("REPO", "${response.code()} --- ${response.errorBody().toString()}")
            }
        } catch (t: Throwable) {
            Log.e("REPO", "${t.message}")
        }  //hasta aqui no esta retornando nada
    }

    fun getMarsById(id:Int): LiveData<MarsRealState>{
        return getMarsById(id)
    }

    //este value va a contener toda la info de la bD todos los terrenos
    val listAllTerrain: LiveData<List<MarsRealState>> = marsDao.getAllTerrain()

    suspend fun insertTerrain(mars: MarsRealState) {
        marsDao.insertTerrain(mars)
    }

    suspend fun updateTerrain(mars: MarsRealState) {
        marsDao.updateTerrain(mars)
    }

    suspend fun deleteAll(mars: MarsRealState) {
        marsDao.deleteAll()
    }
    fun getMarsByType(String: String) : LiveData<MarsRealState>{
        return marsDao.getMarsByType(String)
    }
    suspend fun  deleteTerrain(mars:MarsRealState){
        marsDao.deleteTerrain(mars)
    }

}