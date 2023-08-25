package com.example.apidog.Model.Remote

import com.example.apidog.Model.Remote.FromInternet.DogDetail
import com.example.apidog.Model.Remote.FromInternet.Dogs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.Objects

interface DogApi {

    @GET("api/breeds/list/all") //hace la solicitud y trae la respuesta
    suspend fun fetchDogList(): Response<List<Dogs>>

    @GET("api/breed/{message}/images/random")
    suspend fun fetchDogDetail(@Path("message") message:String) : Response<DogDetail>

}