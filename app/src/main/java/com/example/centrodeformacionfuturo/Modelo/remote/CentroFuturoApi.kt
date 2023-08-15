package com.example.centrodeformacionfuturo.Modelo.remote

import com.example.centrodeformacionfuturo.Modelo.remote.frominternet.CourseDetail
import com.example.centrodeformacionfuturo.Modelo.remote.frominternet.Courses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CentroFuturoApi {

    @GET("courses") //hace la solicitud y trae la respuesta
    suspend fun fetchCourseList(): Response<List<Courses>>


    @GET("courses/{id}")
    suspend fun fetchCourseDetail(@Path("id") id:String) : Response<CourseDetail>


}