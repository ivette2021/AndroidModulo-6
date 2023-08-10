package com.example.marsapp.Model.Remote


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object { //contructir en kotlin, crea objetos , tiene un acceso limitado y lo podemos llamar de todas partes, instancia del objeto solo se maneja aca

        private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"//copia la url base con la barra, el realstate lo agregamos con el MArsApi


        fun getRetrofit(): MarsApi{ //hereda de nuestra api

            val retrofit = Retrofit.Builder()//concetarse
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//trae los elementos convertidos
                .build() //aplico los cambios


            return retrofit.create(MarsApi::class.java) //return se las pasa a la api quien va a completar con realestate


        }


    }


}