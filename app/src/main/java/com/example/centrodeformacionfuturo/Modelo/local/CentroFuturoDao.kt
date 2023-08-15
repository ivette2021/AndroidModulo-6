package com.example.centrodeformacionfuturo.Modelo.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.centrodeformacionfuturo.Modelo.local.Entities.CoursesDetailEntity
import com.example.centrodeformacionfuturo.Modelo.local.Entities.CoursesEntity


@Dao
interface CentroFuturoDao {


    // insertar lista de Cursos
    @Insert(onConflict = OnConflictStrategy.REPLACE)//si hay duplicado , que simplemente reemplace
    //funciones asincronicas
    suspend fun insertAllCourses(listCourses: List<CoursesEntity>)


    // seleccionar Listado de los Cursos

    @Query("SELECT * FROM courses_list_table ORDER BY id ASC")
    fun getAllCourses(): LiveData<List<CoursesEntity>>

    // inserta 1 Curso
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourseDetail(course: CoursesDetailEntity)

    @Query("SELECT * FROM course_detail_table WHERE id=:id")
    fun getCourseDetailById(id: String): LiveData<CoursesDetailEntity>


}