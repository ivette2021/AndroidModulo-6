package com.example.centrodeformacionfuturo.Modelo.local.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.centrodeformacionfuturo.Modelo.local.CentroFuturoDao
import com.example.centrodeformacionfuturo.Modelo.local.Entities.CoursesDetailEntity
import com.example.centrodeformacionfuturo.Modelo.local.Entities.CoursesEntity


@Database(entities = [CoursesEntity:: class, CoursesDetailEntity::class], version = 1, //colocamos las dos entidades que definimos anteriormente
    exportSchema = false)
abstract class CoursesDataBase: RoomDatabase() {

    // referencia del dao
    abstract fun getCentroFuturoDao(): CentroFuturoDao

//--------------------------------solo cambian un par de parametros como instancias y contexto , porque es la relacion con las entidades y dao
    companion object{

        @Volatile
        private var
                INSTANCE : CoursesDataBase? = null
        fun getDataBase(context: Context) : CoursesDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoursesDataBase::class.java, "Centro_futuro")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
//------------------------------------------siempre se repite------------------------------------------------


}