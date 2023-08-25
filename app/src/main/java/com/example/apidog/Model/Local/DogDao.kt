package com.example.apidog.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apidog.Model.Local.Entities.DogDetailEntity
import com.example.apidog.Model.Local.Entities.DogEntity

@Dao
interface DogDao {

    // insertar lista de plantas
    @Insert(onConflict = OnConflictStrategy.REPLACE)//si hay duplicado , que simplemente reemplace
    //funciones asincronicas
    suspend fun insertAllDogs(listDogs: List<DogEntity>)

    // seleccionar Listado de los plantas

    @Query("SELECT * FROM dog_list_table ORDER BY message ASC")
    fun getAllDogs(): LiveData<List<DogEntity>>

    // inserta 1 item planta
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogDetail(dog: DogDetailEntity)

    @Query("SELECT * FROM dog_detail_table WHERE message=:message")
    fun getDogDetailByMessage(message : String): LiveData<DogDetailEntity>

}
