package com.example.apidog.Model

import com.example.apidog.Model.Local.Entities.DogDetailEntity
import com.example.apidog.Model.Local.Entities.DogEntity
import com.example.apidog.Model.Remote.FromInternet.DogDetail
import com.example.apidog.Model.Remote.FromInternet.Dogs

fun fromInternetDogEntity( dogList: List<Dogs>) :List<DogEntity>{

    return dogList.map {//aca lo que llegue de internet se lo pasaremos a la parte local
        DogEntity( //hay que pasarle todos los datos a la local debido a que remotas y local deben tener todos los campos ocupados
            message = it.message,
            status = it.status
        )
    }
}

fun fromInternetDogDetailEntity( dog: DogDetail) : DogDetailEntity {

    return DogDetailEntity(
        message = dog.message,
        status = dog.status
    )
}