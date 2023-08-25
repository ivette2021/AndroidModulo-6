package com.example.apidog.Model.Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient  {


    companion object{

        private const val BASE_URL ="https://dog.ceo/"

        lateinit var  retrofitInstance : Retrofit

        fun retrofitInstance(): DogApi {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return  retrofit.create(DogApi::class.java)
        }

    }
}