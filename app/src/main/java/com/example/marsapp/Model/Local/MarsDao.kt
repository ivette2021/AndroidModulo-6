package com.example.marsapp.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.marsapp.Model.Remote.MarsRealState

@Dao
interface MarsDao {

//trabajamos con corrutinas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTerrain(mars: MarsRealState) //inserta un terreno

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTerrain(mars: List<MarsRealState>) //insertar varios

    @Update
    suspend fun updateTerrain(mars:MarsRealState)

    @Delete
    suspend fun  deleteTerrain(mars:MarsRealState) //elimina un terreno


    @Query("DELETE FROM mars_table") //elimina toda la lista
    suspend fun deleteAll()

    // traer todos los terrenos
    @Query("SELECT * FROM mars_table ")
    fun getAllMars(): LiveData<List<MarsRealState>>//usando livedata, esto ya seria un return

    @Query("SELECT * FROM mars_table ORDER BY id DESC") //traer todos los terrenos en orden descreciente
    fun getAllTerrain(): LiveData<List<MarsRealState>>


    @Query("SELECT * FROM mars_table WHERE type=:type Limit 1") // filtrar por tipo en venta o alquiler
    fun getMarsByType( type: String): LiveData<MarsRealState>


    @Query("SELECT * FROM mars_table WHERE id=:id")// filtrar por id
    fun getMarsById(id:Int): LiveData<MarsRealState>

}