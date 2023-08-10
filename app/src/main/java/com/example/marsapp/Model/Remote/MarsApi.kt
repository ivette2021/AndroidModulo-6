package com.example.marsapp.Model.Remote

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Response

// incluye los verbos de solicitudes de la api get, post, put,etc

interface MarsApi {

    //peticion http, se hacen por libreria no de forma directa con kotlin
    //sin corrutinas, forma segura solicitudes a internet
    @GET("realestate")
    fun fetchMarsData(): Call<List<MarsRealState>> //call debe ser retrofit2 no otro


// los try catch no se usa
    // con coroutinas
    @GET("realestate")
    suspend fun fetchMarsDataCoroutines(): Response<List<MarsRealState>>

}