package com.example.app_m6.Model.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Task::class], version = 1) //exportamos directamente
//@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDataBase : RoomDatabase() { //nos aseguramos que esta clase sea unica

    //Dao tiene las operaciones de la base de datos
    abstract fun getTaskDao(): TaskDao //conexion dao con la base de datos

    //instancia de forma directa
    companion object {

        @Volatile
        private var INSTANCE: TaskDataBase? = null
        fun getDatabase(context: Context): TaskDataBase {               //obtener nuestro contexto

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {           //sincroniza los hilos en caso de haber mas de uno
                //clase room es el creador de la instancia de la base de datos
                val instance = Room.databaseBuilder(
                    //la base de datos sea una para toda la app
                    context.applicationContext,
                    //nombre del archivo que contiene la base de datos
                    TaskDataBase::class.java,
                    "task_Database"
                ).build() //aplicar los cambios

                INSTANCE = instance
                return instance
            }
        }
    }

}
