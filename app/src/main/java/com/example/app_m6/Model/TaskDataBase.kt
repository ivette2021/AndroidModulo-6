package com.example.app_m6.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDataBase : RoomDatabase() {

    //Dao tiene las operaciones de la base de datos

    abstract fun getTaskDao(): TaskDao


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
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDataBase::class.java,
                    "task_Database"
                ).build() //aplicar los cambios


                INSTANCE = instance
                return instance
            }
        }
    }

}
