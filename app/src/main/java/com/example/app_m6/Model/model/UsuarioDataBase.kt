package com.example.app_m6.Model.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version =1)  //exportamos directamente

abstract class UsuarioDataBase : RoomDatabase() {  //nos aseguramos que esta clase sea unica

    abstract fun getUsuarioDao(): UsuarioDao //instancia del Dao siempre debe estar // conexion con la base de dato
    companion object { //expone un objeto sin instanciar la clase

        //esta variable siempre este disponible
        @Volatile
        private var INSTANCE: UsuarioDataBase? = null
        fun getDatabase(context: Context): UsuarioDataBase {   //obtener nuestro contexto donde ejecutamos los procesos

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
                    UsuarioDataBase::class.java,
                    "usuario_Database"
                ).build() //aplicar los cambios

                INSTANCE = instance
                return instance
            }
        }
    }

}
